import * as paths from "./paths";
import { PublisherResponse } from "./types";
import { getBearerToken } from "./util";

// GET /publisher
export async function getAll(): Promise<PublisherResponse[]> {
  const response = await fetch(paths.publishers.getAll, {
    headers: getBearerToken(),
  });

  return response.json();
}

// DELETE /publisher/:id
export async function deletePublisher(id: number): Promise<void> {
  const response = await fetch(paths.publishers.delete(id), {
    method: "DELETE",
    headers: getBearerToken(),
  });

  if (response.status !== 204) {
    throw new Error(`Unexpected status code: ${response.status}`);
  }

  return Promise.resolve();
}
