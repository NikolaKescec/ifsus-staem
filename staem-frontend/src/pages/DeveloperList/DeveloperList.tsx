import React from "react";

import { useSelector } from "react-redux";

import {
  ActionIcon,
  Center,
  Container,
  Group,
  Pagination,
  Paper,
  Table,
} from "@mantine/core";
import { usePagination } from "@mantine/hooks";
import { IconPencil, IconTrash } from "@tabler/icons";

import * as api from "../../api/developers";
import { DeveloperResponse } from "../../api/types";
import CreateNewCatalogEntry from "../../components/CreateNewCatalogEntry";
import DeleteCatalogEntryModal from "../../components/DeleteCatalogEntryModal";
import UpdateCatalogEntryModal from "../../components/UpdateCatalogEntryModal";
import * as actions from "../../store/shared/developer.actions";
import * as selectors from "../../store/shared/developer.selectors";

export default function CategoryList() {
  const result = useSelector(selectors.result);

  const [modalDeveloper, setModalDeveloper] =
    React.useState<DeveloperResponse | null>(null);
  const [deleteModal, setDeleteModal] = React.useState(false);
  const [updateModal, setUpdateModal] = React.useState(false);

  const pageSize = 10;
  const totalPages = Math.ceil(result.length / pageSize);

  const [page, onPageChange] = React.useState(1);
  const pagination = usePagination({
    initialPage: 1,
    total: totalPages,
  });

  const developers = [...result].sort((a, b) => (a.id < b.id ? -1 : 1));

  const handleDelete = (developer: DeveloperResponse) => {
    setModalDeveloper(developer);
    setDeleteModal(true);
  };

  const handleUpdate = (developer: DeveloperResponse) => {
    setModalDeveloper(developer);
    setUpdateModal(true);
  };

  return (
    <Container size="md">
      <CreateNewCatalogEntry
        title="developer"
        createFunction={api.create}
        dispatchAction={actions.findAll}
      />
      <Paper p={10} my={20}>
        <Table
          verticalSpacing="sm"
          horizontalSpacing="md"
          fontSize="lg"
          highlightOnHover
        >
          <thead>
            <th>ID</th>
            <th>Name</th>
            <th>Actions</th>
          </thead>
          <tbody>
            {developers.map((developer: DeveloperResponse) => {
              if (
                page * pageSize - pageSize <= developer.id &&
                developer.id < page * pageSize
              ) {
                return (
                  <tr key={developer.id}>
                    <td>{developer.id}</td>
                    <td>{developer.name}</td>
                    <td>
                      <Group position="center">
                        <ActionIcon onClick={() => handleUpdate(developer)}>
                          <IconPencil color="yellow" />
                        </ActionIcon>
                        <ActionIcon onClick={() => handleDelete(developer)}>
                          <IconTrash color="red" />
                        </ActionIcon>
                      </Group>
                    </td>
                  </tr>
                );
              }
            })}
          </tbody>
        </Table>
      </Paper>

      {updateModal && modalDeveloper && (
        <UpdateCatalogEntryModal
          title="developer"
          modalOpen={updateModal}
          setModalOpen={setUpdateModal}
          item={modalDeveloper}
          updateFunction={api.update}
          dispatchAction={actions.findAll}
        />
      )}

      {deleteModal && modalDeveloper && (
        <DeleteCatalogEntryModal
          title="developer"
          modalOpen={deleteModal}
          setModalOpen={setDeleteModal}
          item={modalDeveloper}
          deleteFunction={api.deleteDeveloper}
          dispatchAction={actions.findAll}
        />
      )}

      <Center my={20}>
        <Pagination
          total={totalPages}
          {...pagination}
          onChange={(page: number) => onPageChange(page)}
        />
      </Center>
    </Container>
  );
}
