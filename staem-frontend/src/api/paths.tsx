import { createQuery } from "./util";

const api = {
  articles: "/articles",
};

export const articles = {
  findAll: (query: any) => `${api.articles}/?${createQuery(query)}`,
};
