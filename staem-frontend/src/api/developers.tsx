import * as paths from "./paths";
import {
  DeveloperResponse,
  UpdateCatalogValues,
  ValidationError,
} from "./types";
import { getBearerToken, transformErrors } from "./util";

// GET /developer
export async function findAll(): Promise<DeveloperResponse[]> {
  const response = await fetch(paths.developers.findAll, {
    headers: getBearerToken(),
  });

  return response.json();
}

// POST /developer
export async function create(name: string): Promise<void> {
  const response = await fetch(paths.developers.create, {
    method: "POST",
    headers: getBearerToken(),
    body: JSON.stringify({ name }),
  });

  if (response.status === 400) {
    const data = await response.json();
    throw new ValidationError(transformErrors(data));
  }
}

// PUT /developer/:id
export async function update(values: UpdateCatalogValues): Promise<void> {
  const response = await fetch(paths.developers.update(values.id), {
    method: "PUT",
    headers: getBearerToken(),
    body: JSON.stringify(values),
  });

  if (response.status === 400) {
    const data = await response.json();
    throw new ValidationError(transformErrors(data));
  }
}

// DELETE /developer/:id
export async function deleteDeveloper(id: number): Promise<void> {
  const response = await fetch(paths.developers.delete(id), {
    method: "DELETE",
    headers: getBearerToken(),
  });

  if (response.status === 400) {
    const data = await response.json();
    throw new ValidationError(transformErrors(data));
  }
}
