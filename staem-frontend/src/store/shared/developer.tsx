import { createSlice } from "@reduxjs/toolkit";

import { DeveloperResponse } from "../../api/types";
import * as actions from "./developer.actions";
import { StateWithStatus } from "../types";

export type State = StateWithStatus<DeveloperResponse[]>;

const initialState = {
  result: [],
  status: "idle",
} as State;

const developerSlice = createSlice({
  initialState,
  name: "developerSlice",
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

export const developer = developerSlice.reducer;
