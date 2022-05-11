import React from "react";

import { formList, useForm } from "@mantine/form";

import { CreateArticleCommand } from "../../../api/types";
import {
  Box,
  Button,
  Container,
  Group,
  Text,
  useMantineTheme,
} from "@mantine/core";
import ArticleForm from "../componetns/ArticleForm";
import { IconCirclePlus } from "@tabler/icons";

export default function ArticleNew() {
  const form = useForm<CreateArticleCommand>({
    initialValues: {
      type: "",
      title: "",
      description: "",
      price: "",
      currency: "",
      pictureUrl: "",
      releaseDate: "",
      categories: formList([]),
      developers: formList([]),
      genres: formList([]),
      publishers: formList([]),
      pictures: formList([]),
    },
  });

  const onSubmit = (values: CreateArticleCommand) => {
    console.log(values);
  };

  return (
    <Container>
      <form onSubmit={form.onSubmit(onSubmit)}>
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
