import { createSlice } from "@reduxjs/toolkit";

import {
  ArticleDetailsResponse,
  ArticlesFilter,
  Status,
} from "../../../api/types";
import * as actions from "./ArticleUpdate.actions";
import { StateWithStatus } from "../../../store/types";

export type Filter = ArticlesFilter;

type Result = ArticleDetailsResponse;

export type State = StateWithStatus<Result>;

const initialState = {
  status: "idle" as Status,
} as State;

const articleUpdateSlice = createSlice({
  initialState,
  name: "articleUpdate",
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

export const articleUpdate = articleUpdateSlice.reducer;
