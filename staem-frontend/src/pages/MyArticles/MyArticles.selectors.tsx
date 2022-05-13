import { createSelector } from "@reduxjs/toolkit";

import { State } from "./MyArticles.slice";
import { RootState } from "../../store/reducer";

export const result = createSelector(
  (state: RootState) => state.myArticles,
  (state: State) => {
    if (state.status === "success") {
      return state.result;
    } else {
      throw new Error("MyArticles not loaded");
    }
  }
);

export const status = createSelector(
  (state: RootState) => state.myArticles,
  (state: State) => state.status
);
