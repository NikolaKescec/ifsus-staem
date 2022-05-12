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

import * as api from "../../api/genres";
import { GenreResponse } from "../../api/types";
import CreateNewCatalogEntry from "../../components/CreateNewCatalogEntry";
import * as actions from "../../store/shared/genre.actions";
import * as selectors from "../../store/shared/genre.selectors";
import UpdateCatalogEntryModal from "../../components/UpdateCatalogEntryModal";
import DeleteCatalogEntryModal from "../../components/DeleteCatalogEntryModal";

export default function GenreList() {
  const result = useSelector(selectors.result);

  const [modalGenre, setModalGenre] = React.useState<GenreResponse | null>(
    null
  );
  const [deleteModal, setDeleteModal] = React.useState(false);
  const [updateModal, setUpdateModal] = React.useState(false);

  const pageSize = 10;
  const totalPages = Math.ceil(result.length / pageSize);

  const [page, onPageChange] = React.useState(1);
  const pagination = usePagination({
    initialPage: 1,
    total: totalPages,
  });

  const genres = [...result].sort((a, b) => (a.id < b.id ? -1 : 1));

  const handleDelete = (genre: GenreResponse) => {
    setModalGenre(genre);
    setDeleteModal(true);
  };

  const handleUpdate = (genre: GenreResponse) => {
    setModalGenre(genre);
    setUpdateModal(true);
  };

  return (
    <Container size="md">
      <CreateNewCatalogEntry
        title="genre"
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
            {genres.map((genre: GenreResponse) => {
              if (
                page * pageSize - pageSize < genre.id &&
                genre.id <= page * pageSize
              ) {
                return (
                  <tr key={genre.id}>
                    <td>{genre.id}</td>
                    <td>{genre.name}</td>
                    <td>
                      <Group position="center">
                        <ActionIcon onClick={() => handleUpdate(genre)}>
                          <IconPencil color="yellow" />
                        </ActionIcon>
                        <ActionIcon onClick={() => handleDelete(genre)}>
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

      {updateModal && modalGenre && (
        <UpdateCatalogEntryModal
          title="genre"
          modalOpen={updateModal}
          setModalOpen={setUpdateModal}
          item={modalGenre}
          updateFunction={api.update}
          dispatchAction={actions.findAll}
        />
      )}

      {deleteModal && modalGenre && (
        <DeleteCatalogEntryModal
          title="genre"
          modalOpen={deleteModal}
          setModalOpen={setDeleteModal}
          item={modalGenre}
          deleteFunction={api.deleteGenre}
          dispatchAction={actions.findAll}
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
