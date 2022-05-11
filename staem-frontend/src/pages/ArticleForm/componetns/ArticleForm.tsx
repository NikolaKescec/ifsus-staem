import React from "react";

import { useSelector } from "react-redux";

import {
  Card,
  Grid,
  MultiSelect,
  NumberInput,
  Select,
  Stack,
  Text,
  Textarea,
  TextInput,
} from "@mantine/core";
import { DatePicker } from "@mantine/dates";
import { UseFormReturnType } from "@mantine/form/lib/use-form";

import { CreateArticleCommand } from "../../../api/types";
import { CURRENCY_LIST } from "../../../constants/currency";
import ArticleTypePicker from "./ArticleTypePicker";
import ImageListForm from "./ImageListForm";
import PosterImageForm from "./PosterImageForm";
import * as categorySelectors from "../../../store/shared/category.selectors";
import * as developerSelectors from "../../../store/shared/developer.selectors";
import * as genreSelectors from "../../../store/shared/genre.selectors";
import * as publisherSelectors from "../../../store/shared/publisher.selectors";
import { mapDataToSelectItems } from "../../../util/selectUtils";

type ArticleFormProps = {
  form: UseFormReturnType<CreateArticleCommand>;
};

export default function ArticleForm({ form }: ArticleFormProps) {
  const categories = useSelector(categorySelectors.result);
  const developers = useSelector(developerSelectors.result);
  const genres = useSelector(genreSelectors.result);
  const publishers = useSelector(publisherSelectors.result);

  return (
    <Stack spacing={20}>
      <Card>
        <ArticleTypePicker form={form} />
        <Grid>
          <Grid.Col span={4}>
            <TextInput label="Title" {...form.getInputProps("title")} />
          </Grid.Col>
          <Grid.Col span={4}>
            <DatePicker
              label="Release Date"
              {...form.getInputProps("releaseDate")}
            />
          </Grid.Col>
          <Grid.Col span={2}>
            <NumberInput
              label="Price"
              min={0}
              max={100}
              step={0.5}
              precision={2}
              {...form.getInputProps("price")}
            />
          </Grid.Col>
          <Grid.Col span={2}>
            <Select
              label="Currency"
              data={CURRENCY_LIST}
              searchable={true}
              {...form.getInputProps("currency")}
            />
          </Grid.Col>
          <Grid.Col span={6}>
            <MultiSelect
              label="Categories"
              data={mapDataToSelectItems(categories)}
              {...form.getInputProps("categories")}
            />
          </Grid.Col>
          <Grid.Col span={6}>
            <MultiSelect
              label="Genres"
              data={mapDataToSelectItems(genres)}
              {...form.getInputProps("genres")}
            />
          </Grid.Col>
          <Grid.Col span={6}>
            <MultiSelect
              label="Developers"
              data={mapDataToSelectItems(developers)}
              {...form.getInputProps("developers")}
            />
          </Grid.Col>
          <Grid.Col span={6}>
            <MultiSelect
              label="Publishers"
              data={mapDataToSelectItems(publishers)}
              {...form.getInputProps("publishers")}
            />
          </Grid.Col>
        </Grid>

        <Textarea
          my={10}
          autosize={true}
          minRows={5}
          label="Description"
          {...form.getInputProps("description")}
        />
      </Card>

      <Card>
        <Grid>
          <Grid.Col span={4}>
            <Text>Poster image</Text>
            <PosterImageForm form={form} />
          </Grid.Col>
          <Grid.Col span={8}>
            <Text>Other images</Text>
            <ImageListForm form={form} />
          </Grid.Col>
        </Grid>
      </Card>
    </Stack>
  );
}
