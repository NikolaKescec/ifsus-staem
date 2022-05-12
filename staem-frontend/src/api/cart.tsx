import * as paths from "./paths";
import { CreateCartCommand, ValidationError } from "./types";
import { getBearerToken, transformErrors } from "./util";

// POST /cart
export async function create(command: CreateCartCommand): Promise<void> {
  const response = await fetch(paths.cart.create, {
    method: "POST",
    headers: getBearerToken(),
    body: JSON.stringify(command),
  });

  if (response.status === 400) {
    const data = await response.json();
    throw new ValidationError(transformErrors(data));
  }

  if (response.status !== 201) {
    throw new Error(`Unexpected status code: ${response.status}`);
  }
}
