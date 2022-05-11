import { createSelector } from "@reduxjs/toolkit";

import { State } from "./ArticleUpdate.slice";
import { RootState } from "../../../store/reducer";

export const result = createSelector(
  (state: RootState) => state.articleUpdate,
  (state: State) => state.result
);

export const status = createSelector(
  (state: RootState) => state.articleUpdate,
  (state: State) => state.status
);
