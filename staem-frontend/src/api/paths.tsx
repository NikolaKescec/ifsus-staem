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
  delete: (id: number) => `${api.categories}/${id}`,
};

export const developers = {
  getAll: api.developers,
  delete: (id: number) => `${api.developers}/${id}`,
};

export const genres = {
  getAll: api.genres,
  delete: (id: number) => `${api.genres}/${id}`,
};

export const publishers = {
  getAll: api.publishers,
  delete: (id: number) => `${api.publishers}/${id}`,
};
