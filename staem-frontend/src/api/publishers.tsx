import * as paths from "./paths";
import {
  PublisherResponse,
  UpdateCatalogValues,
  ValidationError,
} from "./types";
import { getBearerToken, transformErrors } from "./util";

// GET /publisher
export async function findAll(): Promise<PublisherResponse[]> {
  const response = await fetch(paths.publishers.findAll, {
    headers: getBearerToken(),
  });

  return response.json();
}

// POST /publisher
export async function create(name: string): Promise<void> {
  const response = await fetch(paths.publishers.create, {
    method: "POST",
    headers: getBearerToken(),
    body: JSON.stringify({ name }),
  });

  if (response.status === 400) {
    const data = await response.json();
    throw new ValidationError(transformErrors(data));
  }
}

// PUT /publisher/:id
export async function update(values: UpdateCatalogValues): Promise<void> {
  const response = await fetch(paths.publishers.update(values.id), {
    method: "PUT",
    headers: getBearerToken(),
    body: JSON.stringify(values),
  });

  if (response.status === 400) {
    const data = await response.json();
    throw new ValidationError(transformErrors(data));
  }
}

// DELETE /publisher/:id
export async function deletePublisher(id: number): Promise<void> {
  const response = await fetch(paths.publishers.delete(id), {
    method: "DELETE",
    headers: getBearerToken(),
  });

  if (response.status === 400) {
    const data = await response.json();
    throw new ValidationError(transformErrors(data));
  }
}
