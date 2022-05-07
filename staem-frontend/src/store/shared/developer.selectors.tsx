import { createSelector } from "@reduxjs/toolkit";

import { State } from "./developer";
import { RootState } from "../reducer";

export const status = createSelector(
  (state: RootState) => state.developer,
  (state: State) => state.status
);

export const result = createSelector(
  (state: RootState) => state.developer,
  (state: State) => {
    if (state.status === "success") {
      return state.result;
    } else {
      throw new Error("Developers not loaded");
    }
  }
);
