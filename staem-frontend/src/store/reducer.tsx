import { combineReducers } from "@reduxjs/toolkit";

import { articleList } from "../pages/ArticleList/ArticleList.slice";
import { registry } from "./shared/registry";

const pageReducers = { articleList };

const sharedReducers = {
  registry,
};

export const rootReducer = combineReducers({
  ...pageReducers,
  ...sharedReducers,
});

export type RootState = ReturnType<typeof rootReducer>;
