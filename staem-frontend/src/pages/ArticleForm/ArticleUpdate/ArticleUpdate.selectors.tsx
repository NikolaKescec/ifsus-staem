import { createSelector } from "@reduxjs/toolkit";

import { State } from "./ArticleUpdate.slice";
import { RootState } from "../../../store/reducer";

export const result = createSelector(
  (state: RootState) => state.articleUpdate,
  (state: State) => {
    if (state.status === "success") {
      return state.result;
    } else {
      throw new Error("ArticleUpdate not loaded");
    }
  }
);

export const status = createSelector(
  (state: RootState) => state.articleUpdate,
  (state: State) => state.status
);
