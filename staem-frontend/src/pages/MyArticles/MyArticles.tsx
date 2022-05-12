import React from "react";

import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";

import {
  Badge,
  Card,
  Center,
  Container,
  Grid,
  Group,
  Image,
  Pagination,
  Paper,
  Stack,
  Text,
} from "@mantine/core";
import { usePagination } from "@mantine/hooks";

import ErrorAlert from "../../components/ErrorAlert";
import Spinner from "../../components/Spinner";
import PriceDisplay from "../../components/PriceDisplay";
import * as actions from "./MyArticles.actions";
import * as selectors from "./MyArticles.selectors";
import { useAppDispatch } from "../../store/store";
import { ArticleResponse } from "../../api/types";

export default function MyArticles() {
  const dispatch = useAppDispatch();
  const status = useSelector(selectors.status);

  React.useEffect(() => {
    dispatch(actions.findArticles());
  }, [dispatch]);

  if (status === "error") {
    return <ErrorAlert />;
  }

  if (status !== "success") {
    return <Spinner />;
  }

  return <MyArticlesList />;
}

function MyArticlesList() {
  const result = useSelector(selectors.result);

  const totalPages = Math.ceil(result!.length / 10);

  const [page, onPageChange] = React.useState(1);
  const pagination = usePagination({
    initialPage: page,
    total: totalPages,
  });

  if (totalPages === 0) {
    return (
      <Center>
        <Card>
          <Text>You don't have any articles yet.</Text>
        </Card>
      </Center>
    );
  }

  return (
    <Container>
      <Stack align="stretch" spacing={5}>
        {result?.map((article: ArticleResponse) => (
          <ArticleDisplay article={article} key={article.id} />
        ))}
      </Stack>

      {totalPages > 0 && (
        <Center my={20}>
          <Pagination
            total={totalPages}
            {...pagination}
            onChange={(newPage: number) => onPageChange(newPage)}
          />
        </Center>
      )}
    </Container>
  );
}

type ArticleDisplayProps = {
  article: ArticleResponse;
};

function ArticleDisplay({ article }: ArticleDisplayProps) {
  const navigate = useNavigate();

  return (
    <Paper
      onClick={() => navigate(`/article/${article.id}`)}
      sx={(theme) => ({
        "&:hover": {
          backgroundColor:
            theme.colorScheme === "dark"
              ? theme.colors.dark[6]
              : theme.colors.gray[2],
          cursor: "pointer",
        },
      })}
    >
      <Grid>
        <Grid.Col span={4}>
          <Image src={article.pictureUrl} height={125} />
        </Grid.Col>
        <Grid.Col span={6} pl={10} pt={20}>
          <Text>{article.title}</Text>
        </Grid.Col>
        <Grid.Col span={2} pr={20} pt={20}>
          <Group position="right">
            <Badge>
              <PriceDisplay {...article} />
            </Badge>
          </Group>
        </Grid.Col>
      </Grid>
    </Paper>
  );
}
