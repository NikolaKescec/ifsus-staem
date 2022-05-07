import * as paths from "./paths";
import { GenreResponse, UpdateCatalogValues, ValidationError } from "./types";
import { getBearerToken, transformErrors } from "./util";

// GET /genre
export async function findAll(): Promise<GenreResponse[]> {
  const response = await fetch(paths.genres.findAll, {
    headers: getBearerToken(),
  });

  return response.json();
}

// POST /genre
export async function create(name: string): Promise<void> {
  const response = await fetch(paths.genres.create, {
    method: "POST",
    headers: getBearerToken(),
    body: JSON.stringify({ name }),
  });

  if (response.status === 400) {
    const data = await response.json();
    throw new ValidationError(transformErrors(data));
  }
}

// PUT /genre/:id
export async function update(values: UpdateCatalogValues): Promise<void> {
  const response = await fetch(paths.genres.update(values.id), {
    method: "PUT",
    headers: getBearerToken(),
    body: JSON.stringify(values),
  });

  if (response.status === 400) {
    const data = await response.json();
    throw new ValidationError(transformErrors(data));
  }
}

// DELETE /genre/:id
export async function deleteGenre(id: number): Promise<void> {
  const response = await fetch(paths.genres.delete(id), {
    method: "DELETE",
    headers: getBearerToken(),
  });

  if (response.status === 400) {
    const data = await response.json();
    throw new ValidationError(transformErrors(data));
  }
}
