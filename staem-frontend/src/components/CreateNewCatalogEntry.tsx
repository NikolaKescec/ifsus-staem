import React from "react";

import { Button, Grid, Paper, Space, Text, TextInput } from "@mantine/core";
import { useForm } from "@mantine/form";
import { showNotification } from "@mantine/notifications";
import { IconCheck, IconCirclePlus, IconCircleX } from "@tabler/icons";

import { AsyncThunk } from "@reduxjs/toolkit";
import { useAppDispatch } from "../store/store";

type CreateNewCatalogEntryProps = {
  title: string;
  createFunction: (name: string) => Promise<void>;
  dispatchAction: AsyncThunk<any[], void, {}>;
};

type NewForm = {
  name: string;
};

export default function CreateNewCatalogEntry({
  title,
  createFunction,
  dispatchAction,
}: CreateNewCatalogEntryProps) {
  const dispatch = useAppDispatch();

  const form = useForm({
    initialValues: {
      name: "",
    },
    validate: {
      name: (value) => (value.trim().length > 0 ? null : "Name is required"),
    },
  });

  const onSubmit = async (values: NewForm) => {
    try {
      await createFunction(values.name);

      showNotification({
        title: "Success",
        message: `${title} successfully created`,
        color: "green",
        icon: <IconCheck />,
      });

      dispatch(dispatchAction());
    } catch (error: any) {
      form.setFieldError("name", error.errors.name);
      showNotification({
        title: "Error",
        message: `An error occurred while creating the ${title}`,
        color: "red",
        icon: <IconCircleX />,
      });
    }
  };

  return (
    <Paper p={20}>
      <Text align="center">Create new {title}</Text>
      <form onSubmit={form.onSubmit(onSubmit)}>
        <Grid>
          <Grid.Col span={10}>
            <TextInput label="Name" {...form.getInputProps("name")} />
          </Grid.Col>
          <Grid.Col span={2}>
            <Space h="md" />
            <Space h="xs" />
            <Button type="submit" leftIcon={<IconCirclePlus />}>
              Create
            </Button>
          </Grid.Col>
        </Grid>
      </form>
    </Paper>
  );
}
