import { createSelector } from "@reduxjs/toolkit";

import { State } from "./ArticleList.slice";
import { RootState } from "../../store/reducer";

export const result = createSelector(
  (state: RootState) => state.articleList,
  (state: State) => state.result
);

export const status = createSelector(
  (state: RootState) => state.articleList,
  (state: State) => state.status
);

export const filter = createSelector(
  (state: RootState) => state.articleList,
  (state: State) => state.filter
);

export const page = createSelector(
  (state: RootState) => state.articleList,
  (state: State) => state.page
);
