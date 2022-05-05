export type StateWithStatus<T> =
  | {
      status: "waiting" | "idle" | "error";
      result?: T;
    }
  | {
      status: "success";
      result: T;
    };
