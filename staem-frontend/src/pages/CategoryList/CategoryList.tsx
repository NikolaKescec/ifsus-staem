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

import * as api from "../../api/categories";
import { CategoryResponse } from "../../api/types";
import CreateNewCatalogEntry from "../../components/CreateNewCatalogEntry";
import DeleteCatalogEntryModal from "../../components/DeleteCatalogEntryModal";
import UpdateCatalogEntryModal from "../../components/UpdateCatalogEntryModal";
import * as actions from "../../store/shared/category.actions";
import * as selectors from "../../store/shared/category.selectors";

export default function CategoryList() {
  const result = useSelector(selectors.result);

  const [modalCategory, setModalCategory] =
    React.useState<CategoryResponse | null>(null);
  const [deleteModal, setDeleteModal] = React.useState(false);
  const [updateModal, setUpdateModal] = React.useState(false);

  const pageSize = 10;
  const totalPages = Math.ceil(result.length / pageSize);

  const [page, onPageChange] = React.useState(1);
  const pagination = usePagination({
    initialPage: 1,
    total: totalPages,
  });

  const categories = [...result].sort((a, b) => (a.id < b.id ? -1 : 1));

  const handleDelete = (category: CategoryResponse) => {
    setModalCategory(category);
    setDeleteModal(true);
  };

  const handleUpdate = (category: CategoryResponse) => {
    setModalCategory(category);
    setUpdateModal(true);
  };

  return (
    <Container size="md">
      <CreateNewCatalogEntry
        title="category"
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
            {categories.map((category: CategoryResponse) => {
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
                        <ActionIcon onClick={() => handleUpdate(category)}>
                          <IconPencil color="yellow" />
                        </ActionIcon>
                        <ActionIcon onClick={() => handleDelete(category)}>
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

      {updateModal && modalCategory && (
        <UpdateCatalogEntryModal
          title="category"
          modalOpen={updateModal}
          setModalOpen={setUpdateModal}
          item={modalCategory}
          updateFunction={api.update}
          dispatchAction={actions.findAll}
        />
      )}

      {deleteModal && modalCategory && (
        <DeleteCatalogEntryModal
          title="category"
          modalOpen={deleteModal}
          setModalOpen={setDeleteModal}
          item={modalCategory}
          deleteFunction={api.deleteCategory}
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
