import { createSlice } from "@reduxjs/toolkit";

import { ArticleResponse, FindArticlesQuery, Status } from "../../api/types";
import * as actions from "./ArticleList.actions";
import { StateWithStatus } from "../../store/types";

type Filter = FindArticlesQuery;

type Result = ArticleResponse[];

export type State = StateWithStatus<Result> & {
  page: {
    pageNumber: number;
    pageSize: number;
    totalElements: number;
  };
  filter: Filter;
};

const initialState = {
  page: {
    pageNumber: 0,
    pageSize: 10,
    totalElements: 0,
  },
  filter: {},
  status: "idle" as Status,
} as State;

const articleListSlice = createSlice({
  initialState,
  name: "articleList",
  reducers: {
    filter: (state, action) => {
      state.filter = action.payload;
      state.page.pageNumber = 0;
    },
    page: (state, action) => {
      state.page.pageNumber = action.payload;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(actions.findAll.pending, (state) => {
        state.result = initialState.result;
        state.status = "waiting";
      })
      .addCase(actions.findAll.rejected, (state) => {
        state.status = "error";
      })
      .addCase(actions.findAll.fulfilled, (state, action) => {
        state.status = "success";
        state.result = action.payload.content;
        state.page = {
          pageNumber: action.payload.pageable.pageNumber,
          pageSize: action.payload.pageable.pageSize,
          totalElements: action.payload.totalElements,
        };
      });
  },
});

export const articleList = articleListSlice.reducer;
