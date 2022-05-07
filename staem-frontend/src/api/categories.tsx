import * as paths from "./paths";
import { CategoryResponse } from "./types";
import { getBearerToken } from "./util";

// GET /category
export async function findAll(): Promise<CategoryResponse[]> {
  const response = await fetch(paths.categories.getAll, {
    headers: getBearerToken(),
  });

  return response.json();
}
