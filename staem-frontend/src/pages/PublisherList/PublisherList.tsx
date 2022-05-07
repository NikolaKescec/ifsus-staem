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

import * as api from "../../api/publishers";
import { PublisherResponse } from "../../api/types";
import CreateNewCatalogEntry from "../../components/CreateNewCatalogEntry";
import * as actions from "../../store/shared/publisher.actions";
import * as selectors from "../../store/shared/publisher.selectors";
import { useAppDispatch } from "../../store/store";

export default function CategoryList() {
  const navigate = useNavigate();
  const dispatch = useAppDispatch();

  const result = useSelector(selectors.result);

  const [publisherToDelete, setPublisherToDelete] =
    React.useState<PublisherResponse | null>(null);
  const [modalOpen, setModalOpen] = React.useState(false);

  const pageSize = 10;
  const totalPages = Math.ceil(result.length / pageSize);

  const [page, onPageChange] = React.useState(1);
  const pagination = usePagination({
    initialPage: 1,
    total: totalPages,
  });

  const publishers = [...result].sort((a, b) => (a.id < b.id ? -1 : 1));

  const handleDelete = (publisher: PublisherResponse) => {
    setPublisherToDelete(publisher);
    setModalOpen(true);
  };

  const onDelete = async () => {
    setModalOpen(false);

    try {
      await api.deletePublisher(publisherToDelete!.id);

      showNotification({
        title: "Success",
        message: "Publisher deleted successfully",
        color: "green",
        icon: <IconCheck />,
      });

      dispatch(actions.findAll());
    } catch (error) {
      showNotification({
        title: "Error",
        message: "An error occurred while deleting the publisher",
        color: "red",
        icon: <IconCircleX />,
      });
    }
  };

  return (
    <Container size="md">
      <CreateNewCatalogEntry
        title="publisher"
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
            {publishers.map((publisher: PublisherResponse) => {
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
                        <ActionIcon
                          onClick={() =>
                            navigate(`/publishers/${publisher.id}/update`)
                          }
                        >
                          <IconPencil color="yellow" />
                        </ActionIcon>
                        <ActionIcon onClick={() => handleDelete(publisher)}>
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
        title="Delete Publisher"
      >
        <Text>
          Are you sure you want to delete{" "}
          <Mark color="gray" px={5} py={2}>
            {publisherToDelete?.name}
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
