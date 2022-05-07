import { createSelector } from "@reduxjs/toolkit";

import { State } from "./category";
import { RootState } from "../reducer";

export const status = createSelector(
  (state: RootState) => state.category,
  (state: State) => state.status
);

export const result = createSelector(
  (state: RootState) => state.category,
  (state: State) => {
    if (state.status === "success") {
      return state.result;
    } else {
      throw new Error("Categories not loaded");
    }
  }
);
