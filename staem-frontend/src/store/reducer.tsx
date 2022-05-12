import { combineReducers } from "@reduxjs/toolkit";

import { articleList } from "../pages/ArticleList/ArticleList.slice";
import { articleDetails } from "../pages/ArticleDetails/ArticleDetails.slice";
import { articleUpdate } from "../pages/ArticleForm/ArticleUpdate/ArticleUpdate.slice";
import { myArticles } from "../pages/MyArticles/MyArticles.slice";
import { cart } from "./shared/cart";
import { category } from "./shared/category";
import { developer } from "./shared/developer";
import { genre } from "./shared/genre";
import { publisher } from "./shared/publisher";
import { user } from "./shared/user";

const pageReducers = { articleList, articleDetails, articleUpdate, myArticles };

const sharedReducers = {
  cart,
  category,
  developer,
  genre,
  publisher,
  user,
};

export const rootReducer = combineReducers({
  ...pageReducers,
  ...sharedReducers,
});

export type RootState = ReturnType<typeof rootReducer>;
