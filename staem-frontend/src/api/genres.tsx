import * as paths from "./paths";
import { GenreResponse } from "./types";
import { getBearerToken } from "./util";

// GET /genre
export async function findAll(): Promise<GenreResponse[]> {
  const response = await fetch(paths.genres.getAll, {
    headers: getBearerToken(),
  });

  return response.json();
}

// DELETE /genre/:id
export async function deleteGenre(id: number): Promise<void> {
  const response = await fetch(paths.genres.delete(id), {
    method: "DELETE",
    headers: getBearerToken(),
  });

  if (response.status !== 204) {
    throw new Error(`Unexpected status code: ${response.status}`);
  }

  return Promise.resolve();
}
