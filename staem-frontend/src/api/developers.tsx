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
