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

import { GenreResponse } from "../../api/types";
import * as genreSelectors from "../../store/shared/genre.selectors";

export default function GenreList() {
  const genres = useSelector(genreSelectors.result);

  const pageSize = 10;
  const totalPages = Math.ceil(genres.length / pageSize);

  const [page, onPageChange] = React.useState(1);
  const pagination = usePagination({
    initialPage: 1,
    total: totalPages,
  });

  const sortedGenres = [...genres].sort((a, b) => (a.id < b.id ? -1 : 1));

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
            {sortedGenres.map((genre: GenreResponse) => {
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
                        <ActionIcon>
                          <IconPencil color="yellow" />
                        </ActionIcon>
                        <ActionIcon>
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
