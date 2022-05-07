import * as paths from "./paths";
import {
  CategoryResponse,
  UpdateCatalogValues,
  ValidationError,
} from "./types";
import { getBearerToken, transformErrors } from "./util";

// GET /category
export async function findAll(): Promise<CategoryResponse[]> {
  const response = await fetch(paths.categories.findAll, {
    headers: getBearerToken(),
  });

  return response.json();
}

// POST /category
export async function create(name: string): Promise<void> {
  const response = await fetch(paths.categories.create, {
    method: "POST",
    headers: getBearerToken(),
    body: JSON.stringify({ name }),
  });

  if (response.status === 400) {
    const data = await response.json();
    throw new ValidationError(transformErrors(data));
  }
}

// PUT /category/:id
export async function update(values: UpdateCatalogValues): Promise<void> {
  const response = await fetch(paths.categories.update(values.id), {
    method: "PUT",
    headers: getBearerToken(),
    body: JSON.stringify(values),
  });

  if (response.status === 400) {
    const data = await response.json();
    throw new ValidationError(transformErrors(data));
  }
}

// DELETE /category/:id
export async function deleteCategory(id: number): Promise<void> {
  const response = await fetch(paths.categories.delete(id), {
    method: "DELETE",
    headers: getBearerToken(),
  });

  if (response.status === 400) {
    const data = await response.json();
    throw new ValidationError(transformErrors(data));
  }
}
