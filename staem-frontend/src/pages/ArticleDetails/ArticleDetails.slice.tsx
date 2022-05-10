import { createSlice } from "@reduxjs/toolkit";

import {
  ArticleDetailsResponse,
  ArticleResponse,
  ArticlesFilter,
  Status,
} from "../../api/types";
import * as actions from "./ArticleDetails.actions";
import { StateWithStatus } from "../../store/types";

export type Filter = ArticlesFilter;

type Result = ArticleDetailsResponse;

export type State = StateWithStatus<Result>;

const initialState = {
  status: "idle" as Status,
} as State;

const articleDetailsSlice = createSlice({
  initialState,
  name: "articleDetails",
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(actions.findById.pending, (state) => {
        state.result = initialState.result;
        state.status = "waiting";
      })
      .addCase(actions.findById.rejected, (state) => {
        state.status = "error";
      })
      .addCase(actions.findById.fulfilled, (state, action) => {
        state.status = "success";
        state.result = action.payload;
      });
  },
});

export const articleDetails = articleDetailsSlice.reducer;
