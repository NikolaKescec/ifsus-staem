import React from "react";

import { Autocomplete, Grid, Loader, Select, Text } from "@mantine/core";
import { useDebouncedValue } from "@mantine/hooks";
import { UseFormReturnType } from "@mantine/form/lib/use-form";
import { IconSearch } from "@tabler/icons";

import * as articleApi from "../../../api/articles";
import { CreateArticleCommand } from "../../../api/types";
import { ARTICLE_TYPE_LIST } from "../../../constants/articleType";

type ArticleTypePickerProps = {
  form: UseFormReturnType<CreateArticleCommand>;
};

export default function ArticleTypePicker({ form }: ArticleTypePickerProps) {
  const [gameValue, setGameValue] = React.useState<any>("");
  const [loading, setLoading] = React.useState(false);
  const [games, setGames] = React.useState<{ value: string; label: string }[]>(
    []
  );
  const [deboundedValue] = useDebouncedValue(gameValue, 250);

  React.useEffect(() => {
    const fetchGames = async () =>
      articleApi.findAll({
        title: deboundedValue,
        pageInfo: { page: 0, size: 20 },
      });

    setLoading(true);
    fetchGames().then((res) => {
      const gameOptions = res.content.map((article) => ({
        value: article.id.toString(),
        label: article.title,
      }));

      setLoading(false);
      setGames(gameOptions);
    });
  }, [deboundedValue]);

  const { value } = form.getInputProps("type");

  const onChange = async (query: string) => {
    if (Number(query)) {
      const label = games.filter((game) => game.value === query)[0].label;
      setGameValue(label);
      form.setFieldValue("baseGameId", Number(query));
    } else {
      setGameValue(query);
    }
  };

  return (
    <Grid>
      <Grid.Col span={4}>
        <Select
          label="Article type"
          data={ARTICLE_TYPE_LIST}
          {...form.getInputProps("type")}
        />
      </Grid.Col>
      <Grid.Col span={8}>
        {value === "DLC" && (
          <Autocomplete
            {...form.getInputProps("baseGameId")}
            value={gameValue}
            onChange={onChange}
            data={games}
            icon={<IconSearch />}
            rightSection={loading ? <Loader size={16} /> : null}
            nothingFound={<Text>No game found</Text>}
            label="Base Game"
            placeholder="Start typing..."
            filter={(_, __) => true}
          />
        )}
      </Grid.Col>
    </Grid>
  );
}
