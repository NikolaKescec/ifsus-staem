import React from "react";

import { Container, Stack } from "@mantine/core";

import ArticleListFilter from "./components/ArticleListFilter";
import ArticleListResult from "./components/ArticleListResult";

export default function ArticleList() {
  return (
    <Container>
      <Stack align="stretch" spacing={20}>
        <ArticleListFilter />
        <ArticleListResult />
      </Stack>
    </Container>
  );
}
