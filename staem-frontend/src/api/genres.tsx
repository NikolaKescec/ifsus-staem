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
