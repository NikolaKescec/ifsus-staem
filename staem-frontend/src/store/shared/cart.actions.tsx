import { createAction } from "@reduxjs/toolkit";

import { ArticleResponse } from "../../api/types";

export const addItem = createAction<ArticleResponse>("cart/addItem");

export const clear = createAction("cart/clear");

export const removeItem = createAction<number>("cart/removeItem");
