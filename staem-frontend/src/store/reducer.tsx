import { combineReducers } from "@reduxjs/toolkit";

import { articleList } from "../pages/ArticleList/ArticleList.slice";

const pageReducers = { articleList };

const sharedReducers = {};

export const rootReducer = combineReducers({
  ...pageReducers,
  ...sharedReducers,
});

export type RootState = ReturnType<typeof rootReducer>;
