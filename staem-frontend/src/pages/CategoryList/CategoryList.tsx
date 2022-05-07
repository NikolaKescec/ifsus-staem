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

import { CategoryResponse } from "../../api/types";
import * as registrySelectors from "../../store/shared/registry.selectors";

export default function CategoryList() {
  const categories = useSelector(registrySelectors.categories);

  const pageSize = 10;
  const totalPages = Math.ceil(categories.length / pageSize);

  const [page, onPageChange] = React.useState(1);
  const pagination = usePagination({
    initialPage: 1,
    total: totalPages,
  });

  const sortedCategories = [...categories].sort((a, b) =>
    a.id < b.id ? -1 : 1
  );

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
            {sortedCategories.map((category: CategoryResponse) => {
              if (
                page * pageSize - pageSize <= category.id &&
                category.id < page * pageSize
              ) {
                return (
                  <tr key={category.id}>
                    <td>{category.id}</td>
                    <td>{category.name}</td>
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
