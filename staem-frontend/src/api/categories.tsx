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

// DELETE /category/:id
export async function deleteCategory(id: number): Promise<void> {
  const response = await fetch(paths.categories.delete(id), {
    method: "DELETE",
    headers: getBearerToken(),
  });

  if (response.status !== 204) {
    throw new Error(`Unexpected status code: ${response.status}`);
  }

  return Promise.resolve();
}
