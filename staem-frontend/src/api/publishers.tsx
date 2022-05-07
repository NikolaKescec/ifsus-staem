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
