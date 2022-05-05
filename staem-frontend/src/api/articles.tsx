import axios from "axios";

import * as paths from "./paths";
import { ArticleResponse, FindArticlesQuery, Page } from "./types";
import { createBearerHeader } from "./util";

// GET /articles/:query
export async function findAll(
  query: FindArticlesQuery,
  token?: string
): Promise<Page<ArticleResponse>> {
  console.log(token);

  const response = await axios.get<Page<ArticleResponse>>(
    paths.articles.findAll(query),
    createBearerHeader(token)
  );

  return response.data;
}
