import React from "react";

import { useNavigate, useParams } from "react-router-dom";
import { useSelector } from "react-redux";

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
import { IconCircleCheck, IconCircleX, IconDeviceFloppy } from "@tabler/icons";

import * as articleApi from "../../../api/articles";
import { CreateArticleCommand } from "../../../api/types";
import Spinner from "../../../components/Spinner";
import ArticleForm from "../componetns/ArticleForm";
import * as actions from "./ArticleUpdate.actions";
import * as selectors from "./ArticleUpdate.selectors";
import { useAppDispatch } from "../../../store/store";

export default function ArticleUpdate() {
  const { id } = useParams();
  const dispatch = useAppDispatch();

  const status = useSelector(selectors.status);

  React.useEffect(() => {
    dispatch(actions.findById(Number(id)));
  }, [dispatch, id]);

  if (status !== "success") {
    return <Spinner />;
  }

  return <ArticleUpdateForm />;
}

function ArticleUpdateForm() {
  const result = useSelector(selectors.result);

  const form = useForm<CreateArticleCommand>({
    initialValues: {
      ...result!,
      type: "GAME",
      baseGameId: undefined,
      releaseDate: new Date(result!.releaseDate),
      categories: result!.categories.map((category) => category.id.toString()),
      developers: result!.developers.map((developer) =>
        developer.id.toString()
      ),
      genres: result!.genres.map((genre) => genre.id.toString()),
      publishers: result!.publishers.map((publisher) =>
        publisher.id.toString()
      ),
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
    console.log(values);

    try {
      const response = await articleApi.create(values);

      showNotification({
        message: "Article successfully updated",
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
        <ArticleUpdateHeading />
        <ArticleForm form={form} />
      </form>
    </Container>
  );
}

function ArticleUpdateHeading() {
  const theme = useMantineTheme();

  return (
    <Box mb={20}>
      <Group position="apart">
        <Text
          size="lg"
          weight="bold"
          color={theme.colorScheme === "dark" ? "white" : "black"}
        >
          Update article
        </Text>
        <Group>
          <Button type="submit" rightIcon={<IconDeviceFloppy />}>
            Update
          </Button>
        </Group>
      </Group>
    </Box>
  );
}
