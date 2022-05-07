import * as paths from "./paths";
import { DeveloperResponse } from "./types";
import { getBearerToken } from "./util";

// GET /developer
export async function findAll(): Promise<DeveloperResponse[]> {
  const response = await fetch(paths.developers.getAll, {
    headers: getBearerToken(),
  });

  return response.json();
}

// DELETE /developer/:id
export async function deleteDeveloper(id: number): Promise<void> {
  const response = await fetch(paths.developers.delete(id), {
    method: "DELETE",
    headers: getBearerToken(),
  });

  if (response.status !== 204) {
    throw new Error(`Unexpected status code: ${response.status}`);
  }

  return Promise.resolve();
}
