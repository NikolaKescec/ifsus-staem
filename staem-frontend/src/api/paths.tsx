import { createQuery } from "./util";

const API_BASE_URL = "http://localhost:8080";

const api = {
  articles: `${API_BASE_URL}/article`,
  categories: `${API_BASE_URL}/category`,
  developers: `${API_BASE_URL}/developer`,
  genres: `${API_BASE_URL}/genre`,
  publishers: `${API_BASE_URL}/publisher`,
};

export const articles = {
  findAll: (query: any) => `${api.articles}/?${createQuery(query)}`,
};

export const categories = {
  getAll: api.categories,
};

export const developers = {
  getAll: api.developers,
};

export const genres = {
  getAll: api.genres,
};

export const publishers = {
  getAll: api.publishers,
};
