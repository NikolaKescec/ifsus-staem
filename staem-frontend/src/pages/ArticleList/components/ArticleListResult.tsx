import React from "react";

import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";

import {
  Badge,
  Center,
  Grid,
  Group,
  Image,
  Pagination,
  Paper,
  Stack,
  Text,
} from "@mantine/core";

import { ArticleResponse } from "../../../api/types";
import ErrorAlert from "../../../components/ErrorAlert";
import PriceDisplay from "../../../components/PriceDisplay";
import Spinner from "../../../components/Spinner";
import * as selectors from "../ArticleList.selectors";
import * as actions from "../ArticleList.actions";
import { useAppDispatch } from "../../../store/store";

export default function ArticleListResult() {
  const dispatch = useAppDispatch();

  const filter = useSelector(selectors.filter);
  const page = useSelector(selectors.page);
  const status = useSelector(selectors.status);

  React.useEffect(() => {
    dispatch(
      actions.findAll({
        ...filter,
        priceRange: filter.priceRange
          ? { minPrice: filter.priceRange[0], maxPrice: filter.priceRange[1] }
          : undefined,
        pageInfo: { page: page.pageNumber, size: page.pageSize },
      })
    );
  }, [dispatch, filter, page.pageNumber, page.pageSize]);

  const onPageChange = (pageNumber: number) => {
    dispatch(actions.page(pageNumber));
  };

  if (status === "error") {
    return <ErrorAlert />;
  }

  if (status !== "success") {
    return <Spinner />;
  }

  return <ArticleList />;
}

function ArticleList() {
  const dispatch = useAppDispatch();
  const navigate = useNavigate();

  const result = useSelector(selectors.result);
  const page = useSelector(selectors.page);

  const onPageChange = (pageNumber: number) => {
    dispatch(actions.page(pageNumber - 1));
  };

  return (
    <>
      <Stack align="stretch" spacing={5}>
        {result?.map((article: ArticleResponse) => (
          <Paper
            key={article.id}
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
        ))}
        {page.totalPages > 0 && (
          <Center my={20}>
            <Pagination
              page={page.pageNumber + 1}
              total={page.totalPages}
              onChange={onPageChange}
            />
          </Center>
        )}
      </Stack>
    </>
  );
}
