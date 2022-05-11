import React from "react";

import { useNavigate } from "react-router-dom";

import {
  Box,
  Button,
  Container,
  Group,
  LoadingOverlay,
  Text,
  useMantineTheme,
} from "@mantine/core";
import { useForm } from "@mantine/form";
import { showNotification } from "@mantine/notifications";
import { IconCircleCheck, IconCirclePlus, IconCircleX } from "@tabler/icons";

import * as articleApi from "../../../api/articles";
import { CreateArticleCommand } from "../../../api/types";
import ArticleForm from "../componetns/ArticleForm";

export default function ArticleNew() {
  const form = useForm<CreateArticleCommand>({
    initialValues: {
      type: "",
      title: "",
      baseGameId: undefined,
      price: undefined,
      description: "",
      releaseDate: "",
      currency: "",
      pictureUrl: "",
      categories: [],
      developers: [],
      genres: [],
      publishers: [],
      pictures: [],
    },
    validate: {
      type: (value) =>
        ["GAME", "DLC"].includes(value) ? undefined : "Type is not valid",
      baseGameId: (value, values) => {
        return values.type === "DLC" && !value
          ? "DLC requires base game"
          : undefined;
      },
      title: (value) =>
        value.trim().length > 0 ? undefined : "Title is required",
      releaseDate: (value) => (value ? undefined : "Release date is required"),
      price: (value) => (value && value > 0 ? undefined : "Price is required"),
      currency: (value) =>
        value.trim().length > 0 ? undefined : "Currency is required",
      description: (value) =>
        value.trim().length > 0 ? undefined : "Description is required",
      pictureUrl: (value) =>
        value.trim().length > 0 ? undefined : "Poster image is required",
      categories: (value) =>
        value && value.length > 0 ? undefined : "Select at least one category",
      developers: (value) => {
        return value && value.length > 0
          ? undefined
          : "Select at least one developer";
      },
      genres: (value) =>
        value && value.length > 0 ? undefined : "Select at least one genre",
      publishers: (value) =>
        value.length > 0 ? undefined : "Select at least one publisher",
      pictures: (value) =>
        value.length > 0 ? undefined : "Upload at least one other picture",
    },
  });

  const navigate = useNavigate();
  const [loading, setLoading] = React.useState(false);

  const onSubmit = async (values: CreateArticleCommand) => {
    try {
      const response = await articleApi.create(values);

      showNotification({
        message: "Article successfully created",
        color: "green",
        icon: <IconCircleCheck />,
      });

      navigate(`/article/${response.id}`);
    } catch (error: any) {
      form.setErrors(error.errors);

      showNotification({
        message: "Something went wrong",
        color: "red",
        icon: <IconCircleX />,
      });
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container p={10} style={{ position: "relative" }}>
      <form onSubmit={form.onSubmit(onSubmit)}>
        <LoadingOverlay visible={loading} />
        <ArticleNewHeading />
        <ArticleForm form={form} />
      </form>
    </Container>
  );
}

function ArticleNewHeading() {
  const theme = useMantineTheme();

  return (
    <Box mb={20}>
      <Group position="apart">
        <Text
          size="lg"
          weight="bold"
          color={theme.colorScheme === "dark" ? "white" : "black"}
        >
          Create New Article
        </Text>
        <Group>
          <Button type="submit" rightIcon={<IconCirclePlus />}>
            Create
          </Button>
        </Group>
      </Group>
    </Box>
  );
}
