import React from "react";

import { Button, Group, Mark, Modal, Text } from "@mantine/core";
import { showNotification } from "@mantine/notifications";
import { IconCheck, IconCircleX } from "@tabler/icons";

import { AsyncThunk } from "@reduxjs/toolkit";

import { UpdateCatalogValues } from "../api/types";
import { useAppDispatch } from "../store/store";

type DeleteCatalogEntryModalProps = {
  title: string;
  modalOpen: boolean;
  setModalOpen: (modalOpen: boolean) => void;
  item: UpdateCatalogValues;
  deleteFunction: (id: number) => Promise<void>;
  dispatchAction: AsyncThunk<any[], void, {}>;
};

export default function DeleteCatalogEntryModal({
  title,
  modalOpen,
  setModalOpen,
  item,
  deleteFunction,
  dispatchAction,
}: DeleteCatalogEntryModalProps) {
  const dispatch = useAppDispatch();

  const onDelete = async () => {
    setModalOpen(false);

    try {
      await deleteFunction(item.id);

      showNotification({
        title: "Success",
        message: `${title} deleted successfully`,
        color: "green",
        icon: <IconCheck />,
      });

      dispatch(dispatchAction());
    } catch (error) {
      showNotification({
        title: "Error",
        message: `An error occurred while deleting the ${title}`,
        color: "red",
        icon: <IconCircleX />,
      });
    }
  };

  return (
    <Modal
      opened={modalOpen}
      onClose={() => setModalOpen(false)}
      title={`Delete ${title}`}
    >
      <Text>
        Are you sure you want to delete{" "}
        <Mark color="gray" px={5} py={2}>
          {item.name}
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
  );
}
