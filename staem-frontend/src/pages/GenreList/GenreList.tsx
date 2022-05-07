import React from "react";

import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";

import {
  ActionIcon,
  Button,
  Center,
  Container,
  Group,
  Mark,
  Modal,
  Pagination,
  Paper,
  Table,
  Text,
} from "@mantine/core";
import { usePagination } from "@mantine/hooks";
import { IconCheck, IconCircleX, IconPencil, IconTrash } from "@tabler/icons";
import { showNotification } from "@mantine/notifications";

import * as api from "../../api/genres";
import { DeveloperResponse, GenreResponse } from "../../api/types";
import * as actions from "../../store/shared/genre.actions";
import * as selectors from "../../store/shared/genre.selectors";
import { useAppDispatch } from "../../store/store";

export default function CategoryList() {
  const navigate = useNavigate();
  const dispatch = useAppDispatch();

  const result = useSelector(selectors.result);

  const [genreToDelete, setGenreToDelete] =
    React.useState<GenreResponse | null>(null);
  const [modalOpen, setModalOpen] = React.useState(false);

  const pageSize = 10;
  const totalPages = Math.ceil(result.length / pageSize);

  const [page, onPageChange] = React.useState(1);
  const pagination = usePagination({
    initialPage: 1,
    total: totalPages,
  });

  const genres = [...result].sort((a, b) => (a.id < b.id ? -1 : 1));

  const handleDelete = (developer: DeveloperResponse) => {
    setGenreToDelete(developer);
    setModalOpen(true);
  };

  const onDelete = async () => {
    setModalOpen(false);

    try {
      await api.deleteGenre(genreToDelete!.id);

      showNotification({
        title: "Success",
        message: "Genre deleted successfully",
        color: "green",
        icon: <IconCheck />,
      });

      dispatch(actions.findAll());
    } catch (error) {
      showNotification({
        title: "Error",
        message: "An error occurred while deleting the genre",
        color: "red",
        icon: <IconCircleX />,
      });
    }
  };

  return (
    <Container size="md">
      <Paper p={10}>
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
                page * pageSize - pageSize <= genre.id &&
                genre.id < page * pageSize
              ) {
                return (
                  <tr key={genre.id}>
                    <td>{genre.id}</td>
                    <td>{genre.name}</td>
                    <td>
                      <Group position="center">
                        <ActionIcon
                          onClick={() => navigate(`/genres/${genre.id}/update`)}
                        >
                          <IconPencil color="yellow" />
                        </ActionIcon>
                        <ActionIcon onClick={() => handleDelete(genre)}>
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

      <Modal
        opened={modalOpen}
        onClose={() => setModalOpen(false)}
        title="Delete Category"
      >
        <Text>
          Are you sure you want to delete{" "}
          <Mark color="gray" px={5} py={2}>
            {genreToDelete?.name}
          </Mark>
        </Text>
        <Group position="right" py={20}>
          <Button color="blue" onClick={() => setModalOpen(false)}>
            Close
          </Button>
          <Button color="red" onClick={onDelete}>
            Delete
          </Button>
        </Group>
      </Modal>

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
