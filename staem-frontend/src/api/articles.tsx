import * as paths from "./paths";
import { ArticleResponse, FindArticlesQuery, Page } from "./types";
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
