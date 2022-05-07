import { createSelector } from "@reduxjs/toolkit";

import { State } from "./genre";
import { RootState } from "../reducer";

export const status = createSelector(
  (state: RootState) => state.genre,
  (state: State) => state.status
);

export const result = createSelector(
  (state: RootState) => state.genre,
  (state: State) => {
    if (state.status === "success") {
      return state.result;
    } else {
      throw new Error("Genres not loaded");
    }
  }
);
