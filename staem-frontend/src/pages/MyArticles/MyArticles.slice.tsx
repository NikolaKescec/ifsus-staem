import { createSlice } from "@reduxjs/toolkit";

import { ArticleResponse, Status } from "../../api/types";
import * as actions from "./MyArticles.actions";
import { StateWithStatus } from "../../store/types";

type Result = ArticleResponse[];

export type State = StateWithStatus<Result>;

const initialState = {
  status: "idle" as Status,
} as State;

const myArticlesSlice = createSlice({
  initialState,
  name: "myArticles",
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(actions.findArticles.pending, (state) => {
        state.result = initialState.result;
        state.status = "waiting";
      })
      .addCase(actions.findArticles.rejected, (state) => {
        state.status = "error";
      })
      .addCase(actions.findArticles.fulfilled, (state, action) => {
        state.status = "success";
        state.result = action.payload;
      });
  },
});

export const myArticles = myArticlesSlice.reducer;
