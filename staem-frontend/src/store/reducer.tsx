import { combineReducers } from "@reduxjs/toolkit";

import { articleList } from "../pages/ArticleList/ArticleList.slice";
import { category } from "./shared/category";
import { developer } from "./shared/developer";
import { genre } from "./shared/genre";
import { publisher } from "./shared/publisher";

const pageReducers = { articleList };

const sharedReducers = {
  category,
  developer,
  genre,
  publisher,
};

export const rootReducer = combineReducers({
  ...pageReducers,
  ...sharedReducers,
});

export type RootState = ReturnType<typeof rootReducer>;
