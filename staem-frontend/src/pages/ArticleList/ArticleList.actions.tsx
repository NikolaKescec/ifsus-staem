import { createAction, createAsyncThunk } from "@reduxjs/toolkit";

import * as api from "../../api/articles";
import { ArticlesFilter } from "../../api/types";

type Filter = ArticlesFilter;

export const filter = createAction<Filter>("articleList/filter");

export const reset = createAction("articleList/reset");

export const findAll = createAsyncThunk("articleList/findAll", api.findAll);

export const page = createAction<number>("articleList/page");
