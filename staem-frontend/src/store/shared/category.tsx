import { createSlice } from "@reduxjs/toolkit";

import { CategoryResponse } from "../../api/types";
import * as actions from "./category.actions";
import { StateWithStatus } from "../types";

export type State = StateWithStatus<CategoryResponse[]>;

const initialState = {
  result: [],
  status: "idle",
} as State;

const categorySlice = createSlice({
  initialState,
  name: "categorySlice",
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(actions.findAll.pending, (state) => {
      state.status = "idle";
    });
    builder.addCase(actions.findAll.fulfilled, (state, action) => {
      state.status = "success";
      state.result = action.payload;
    });
    builder.addCase(actions.findAll.rejected, (state) => {
      state.status = "error";
    });
  },
});

export const category = categorySlice.reducer;
