import React from "react";

import { AsyncThunk } from "@reduxjs/toolkit";
import { useSelector } from "react-redux";

import { Button, Group, Modal, TextInput } from "@mantine/core";
import { useForm } from "@mantine/form";
import { showNotification } from "@mantine/notifications";
import { IconCheck, IconCircleX } from "@tabler/icons";

import { UpdateCatalogValues, UserPermissionType } from "../api/types";
import * as userSelectors from "../store/shared/user.selectors";
import { useAppDispatch } from "../store/store";

type UpdateCatalogEntryModalProps = {
  title: string;
  modalOpen: boolean;
  setModalOpen: (value: boolean) => void;
  item: UpdateCatalogValues;
  updateFunction: (values: UpdateCatalogValues) => Promise<void>;
  dispatchAction: AsyncThunk<any[], void, {}>;
  permission: UserPermissionType;
};

export default function UpdateCatalogEntryModal({
  title,
  modalOpen,
  setModalOpen,
  item,
  updateFunction,
  dispatchAction,
  permission,
}: UpdateCatalogEntryModalProps) {
  const dispatch = useAppDispatch();
  const userPermissions = useSelector(userSelectors.permissions);

  const form = useForm({
    initialValues: item,
    validate: {
      name: (value: string) =>
        value.trim().length > 0 ? undefined : "Name is required",
    },
  });

  const onSubmit = async (values: UpdateCatalogValues) => {
    try {
      await updateFunction(values);

      showNotification({
        title: "Success",
        message: `${title} successfully updated`,
        color: "green",
        icon: <IconCheck />,
      });

      setModalOpen(false);
      dispatch(dispatchAction());
    } catch (error: any) {
      form.setErrors(error.errors);
      showNotification({
        title: "Error",
        message: `An error occurred while updating the ${title}`,
        color: "red",
        icon: <IconCircleX />,
      });
    }
  };

  if (!userPermissions.includes(permission)) {
    return null;
  }

  return (
    <Modal opened={modalOpen} onClose={() => setModalOpen(false)} title={title}>
      <form onSubmit={form.onSubmit(onSubmit)}>
        <TextInput label="Name" {...form.getInputProps("name")} />
        <Group position="right" py={20}>
          <Button color="blue" onClick={() => setModalOpen(false)}>
            Close
          </Button>
          <Button type="submit">Update</Button>
        </Group>
      </form>
    </Modal>
  );
}
