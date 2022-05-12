import * as paths from "./paths";
import {
  ArticleDetailsResponse,
  ArticleResponse,
  CreateArticleCommand,
  FindArticlesQuery,
  Page,
  ValidationError,
} from "./types";
import { getBearerToken, transformErrors } from "./util";

// GET /article/:query
export async function findAll(
  query: FindArticlesQuery
): Promise<Page<ArticleResponse>> {
  const response = await fetch(paths.articles.findAll(query), {
    headers: getBearerToken(),
  });

  return response.json();
}

// GET /article/bought
export async function findBought(): Promise<ArticleResponse[]> {
  const response = await fetch(paths.articles.bought, {
    headers: getBearerToken(),
  });

  return response.json();
}

// GET /article/:id
export async function findById(id: number): Promise<ArticleDetailsResponse> {
  const response = await fetch(paths.articles.findById(id), {
    headers: getBearerToken(),
  });

  return response.json();
}

// POST /article
export async function create(
  command: CreateArticleCommand
): Promise<ArticleDetailsResponse> {
  const response = await fetch(paths.articles.create, {
    method: "POST",
    headers: getBearerToken(),
    body: JSON.stringify(command),
  });

  if (response.status === 400) {
    const data = await response.json();
    throw new ValidationError(transformErrors(data));
  }

  return response.json();
}

// PUT /article/:id
export async function update(
  id: number,
  command: CreateArticleCommand
): Promise<ArticleDetailsResponse> {
  const response = await fetch(paths.articles.update(id), {
    method: "PUT",
    headers: getBearerToken(),
    body: JSON.stringify(command),
  });

  if (response.status === 400) {
    const data = await response.json();
    throw new ValidationError(transformErrors(data));
  }

  return response.json();
}

// DELETE /article/:id
export async function deleteArticle(id: number): Promise<void> {
  const response = await fetch(paths.articles.delete(id), {
    method: "DELETE",
    headers: getBearerToken(),
  });

  if (response.status === 400) {
    const data = await response.json();
    throw new ValidationError(transformErrors(data));
  }
}
