import * as paths from "./paths";
import {
  ArticleDetailsResponse,
  ArticleResponse,
  FindArticlesQuery,
  Page,
} from "./types";
import { getBearerToken } from "./util";

// GET /article/:query
export async function findAll(
  query: FindArticlesQuery
): Promise<Page<ArticleResponse>> {
  const response = await fetch(paths.articles.findAll(query), {
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
