import { createSlice } from "@reduxjs/toolkit";

import { ArticleResponse, ArticlesFilter, Status } from "../../api/types";
import * as actions from "./ArticleList.actions";
import { StateWithStatus } from "../../store/types";

export type Filter = ArticlesFilter;

type Result = ArticleResponse[];

export type State = StateWithStatus<Result> & {
  page: {
    pageNumber: number;
    pageSize: number;
    totalPages: number;
  };
  filter: Filter;
};

export const initialFilterState = {
  title: "",
  genreId: "",
  categoryId: "",
  developerId: "",
  publisherId: "",
  priceRange: [0, 100],
} as Filter;

const initialState = {
  page: {
    pageNumber: 0,
    pageSize: 10,
    totalPages: 0,
  },
  filter: initialFilterState,
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
          totalPages: action.payload.totalPages,
        };
      });
  },
});

export const articleList = articleListSlice.reducer;
