import React from "react";

import { useSelector } from "react-redux";

import {
  Button,
  Grid,
  Group,
  Paper,
  RangeSlider,
  Select,
  Stack,
  Text,
  TextInput,
} from "@mantine/core";
import { useForm } from "@mantine/form";
import { IconFilter, IconFilterOff } from "@tabler/icons";

import * as actions from "../ArticleList.actions";
import * as selectors from "../ArticleList.selectors";
import { useAppDispatch } from "../../../store/store";
import { Filter, initialFilterState } from "../ArticleList.slice";
import { ArticlesFilter } from "../../../api/types";

export default function ArticleListFilter() {
  const dispatch = useAppDispatch();
  const filter = useSelector(selectors.filter);

  const form = useForm({
    initialValues: filter,
  });

  const onSubmit = (values: Filter) => {
    console.log(values);
  };

  const onReset = () => {
    form.reset();
    dispatch(actions.filter(initialFilterState as ArticlesFilter));
  };

  return (
    <Paper p={20}>
      <form onSubmit={form.onSubmit(onSubmit)}>
        <Grid gutter="xl">
          <Grid.Col span={4}>
            <TextInput label="Title" {...form.getInputProps("title")} />
          </Grid.Col>
          <Grid.Col span={4}>
            <Select
              label="Genre"
              data={["All", "Published", "Draft"]}
              {...form.getInputProps("genreId")}
            />
          </Grid.Col>
          <Grid.Col span={4}>
            <Select
              label="Category"
              data={["All", "Published", "Draft"]}
              {...form.getInputProps("categoryId")}
            />
          </Grid.Col>
          <Grid.Col span={4}>
            <Select
              label="Publisher"
              data={["All", "Published", "Draft"]}
              {...form.getInputProps("publisherId")}
            />
          </Grid.Col>
          <Grid.Col span={4}>
            <Select
              label="Developer"
              data={["All", "Published", "Draft"]}
              {...form.getInputProps("developerId")}
            />
          </Grid.Col>
          <Grid.Col span={4}>
            <Text>Price</Text>
            <Stack py={10}>
              <RangeSlider size="md" {...form.getInputProps("priceRange")} />
            </Stack>
          </Grid.Col>
        </Grid>
        <Group position="right" mt="md">
          <Button
            type="button"
            color="red"
            leftIcon={<IconFilterOff />}
            onClick={onReset}
          >
            Reset
          </Button>
          <Button type="submit" leftIcon={<IconFilter />}>
            Search
          </Button>
        </Group>
      </form>
    </Paper>
  );
}
