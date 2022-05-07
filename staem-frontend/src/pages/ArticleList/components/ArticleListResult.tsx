import React from "react";
import { useAppDispatch } from "../../../store/store";
import { useSelector } from "react-redux";
import * as selectors from "../ArticleList.selectors";
import * as actions from "../ArticleList.actions";
import { useNavigate } from "react-router-dom";
import {
  Center,
  Grid,
  Image,
  Loader,
  Pagination,
  Paper,
  Stack,
  Text,
} from "@mantine/core";
import { ArticleResponse } from "../../../api/types";

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

  if (status !== "success") {
    return (
      <Center p={30}>
        <Loader />
      </Center>
    );
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
                <Text color="gray">{article.title}</Text>
              </Grid.Col>
              <Grid.Col span={2} pr={20} pt={20}>
                <Text color="gray" align="right" weight="bold">
                  {article.price} {article.currency}
                </Text>
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
