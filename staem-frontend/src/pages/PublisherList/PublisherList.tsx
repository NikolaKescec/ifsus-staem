import React from "react";

import { Navigate } from "react-router-dom";
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

import * as api from "../../api/publishers";
import { CategoryResponse, PublisherResponse } from "../../api/types";
import CreateNewCatalogEntry from "../../components/CreateNewCatalogEntry";
import DeleteCatalogEntryModal from "../../components/DeleteCatalogEntryModal";
import UpdateCatalogEntryModal from "../../components/UpdateCatalogEntryModal";
import * as actions from "../../store/shared/publisher.actions";
import * as selectors from "../../store/shared/publisher.selectors";
import * as userSelectors from "../../store/shared/user.selectors";

export default function PublisherList() {
  const result = useSelector(selectors.result);
  const userPermissions = useSelector(userSelectors.permissions);

  const [modalPublisher, setModalPublisher] =
    React.useState<PublisherResponse | null>(null);
  const [deleteModal, setDeleteModal] = React.useState(false);
  const [updateModal, setUpdateModal] = React.useState(false);

  const pageSize = 10;
  const totalPages = Math.ceil(result.length / pageSize);

  const [page, onPageChange] = React.useState(1);
  const pagination = usePagination({
    initialPage: 1,
    total: totalPages,
  });

  const publishers = [...result].sort((a, b) => (a.id < b.id ? -1 : 1));

  const handleDelete = (publisher: CategoryResponse) => {
    setModalPublisher(publisher);
    setDeleteModal(true);
  };

  const handleUpdate = (publisher: CategoryResponse) => {
    setModalPublisher(publisher);
    setUpdateModal(true);
  };

  if (!userPermissions.includes("create:publisher")) {
    return <Navigate to="/" />;
  }

  return (
    <Container size="md">
      <CreateNewCatalogEntry
        title="publisher"
        createFunction={api.create}
        dispatchAction={actions.findAll}
        permission="create:publisher"
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
            {publishers.map((publisher: PublisherResponse) => {
              if (
                page * pageSize - pageSize < publisher.id &&
                publisher.id <= page * pageSize
              ) {
                return (
                  <tr key={publisher.id}>
                    <td>{publisher.id}</td>
                    <td>{publisher.name}</td>
                    <td>
                      <Group position="center">
                        <ActionIcon onClick={() => handleUpdate(publisher)}>
                          <IconPencil color="yellow" />
                        </ActionIcon>
                        <ActionIcon onClick={() => handleDelete(publisher)}>
                          <IconTrash color="red" />
                        </ActionIcon>
                      </Group>
                    </td>
                  </tr>
                );
              } else {
                return null;
              }
            })}
          </tbody>
        </Table>
      </Paper>

      {updateModal && modalPublisher && (
        <UpdateCatalogEntryModal
          title="publisher"
          modalOpen={updateModal}
          setModalOpen={setUpdateModal}
          item={modalPublisher}
          updateFunction={api.update}
          dispatchAction={actions.findAll}
          permission="update:publisher"
        />
      )}

      {deleteModal && modalPublisher && (
        <DeleteCatalogEntryModal
          title="publisher"
          modalOpen={deleteModal}
          setModalOpen={setDeleteModal}
          item={modalPublisher}
          deleteFunction={api.deletePublisher}
          dispatchAction={actions.findAll}
          permission="delete:publisher"
        />
      )}

      <Center my={20}>
        <Pagination
          total={totalPages}
          {...pagination}
          onChange={(newPage: number) => onPageChange(newPage)}
        />
      </Center>
    </Container>
  );
}
