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
  findById: (id: number) => `${api.articles}/${id}`,
};

export const categories = {
  findAll: api.categories,
  create: api.categories,
  delete: (id: number) => `${api.categories}/${id}`,
  update: (id: number) => `${api.categories}/${id}`,
};

export const developers = {
  findAll: api.developers,
  create: api.developers,
  delete: (id: number) => `${api.developers}/${id}`,
  update: (id: number) => `${api.developers}/${id}`,
};

export const genres = {
  findAll: api.genres,
  create: api.genres,
  delete: (id: number) => `${api.genres}/${id}`,
  update: (id: number) => `${api.genres}/${id}`,
};

export const publishers = {
  findAll: api.publishers,
  create: api.publishers,
  delete: (id: number) => `${api.publishers}/${id}`,
  update: (id: number) => `${api.publishers}/${id}`,
};
