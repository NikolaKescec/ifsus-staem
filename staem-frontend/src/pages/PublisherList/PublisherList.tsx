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

import { PublisherResponse } from "../../api/types";
import * as publisherSelectors from "../../store/shared/publisher.selectors";

export default function PublisherList() {
  const publishers = useSelector(publisherSelectors.result);

  const pageSize = 10;
  const totalPages = Math.ceil(publishers.length / pageSize);

  const [page, onPageChange] = React.useState(1);
  const pagination = usePagination({
    initialPage: 1,
    total: totalPages,
  });

  const sortedPublishers = [...publishers].sort((a, b) =>
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
            {sortedPublishers.map((publisher: PublisherResponse) => {
              if (
                page * pageSize - pageSize <= publisher.id &&
                publisher.id < page * pageSize
              ) {
                return (
                  <tr key={publisher.id}>
                    <td>{publisher.id}</td>
                    <td>{publisher.name}</td>
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
