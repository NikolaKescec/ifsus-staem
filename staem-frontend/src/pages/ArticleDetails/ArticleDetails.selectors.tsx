import { createSelector } from "@reduxjs/toolkit";

import { State } from "./ArticleDetails.slice";
import { RootState } from "../../store/reducer";

export const result = createSelector(
  (state: RootState) => state.articleDetails,
  (state: State) => state.result
);

export const status = createSelector(
  (state: RootState) => state.articleDetails,
  (state: State) => state.status
);
