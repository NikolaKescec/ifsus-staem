import { createSelector } from "@reduxjs/toolkit";

import { State } from "./MyArticles.slice";
import { RootState } from "../../store/reducer";

export const result = createSelector(
  (state: RootState) => state.myArticles,
  (state: State) => state.result
);

export const status = createSelector(
  (state: RootState) => state.myArticles,
  (state: State) => state.status
);
