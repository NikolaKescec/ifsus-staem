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
    dispatch(actions.filter(values));
  };

  const onReset = () => {
    form.reset();
    dispatch(actions.reset());
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
              <RangeSlider
                size="md"
                max={100}
                marks={[
                  { value: 0, label: "0" },
                  { value: 25, label: "25" },
                  { value: 50, label: "50" },
                  { value: 70, label: "75" },
                  { value: 100, label: "100" },
                ]}
                {...form.getInputProps("priceRange")}
              />
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
