import { useAuth0 } from "@auth0/auth0-react";
import { createAction, createAsyncThunk } from "@reduxjs/toolkit";

import * as api from "../../api/articles";
import { FindArticlesQuery } from "../../api/types";

type Filter = FindArticlesQuery;

export const filter = createAction<Filter>("articleList/filter");

export const findAll = createAsyncThunk(
  "articleList/findAll",
  async (query: Filter) => {
    const { isAuthenticated, getAccessTokenSilently } = useAuth0();

    return api.findAll(
      query,
      isAuthenticated ? await getAccessTokenSilently() : undefined
    );
  }
);

export const page = createAction<number>("articleList/page");
