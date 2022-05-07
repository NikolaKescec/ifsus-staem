import { createSlice } from "@reduxjs/toolkit";

import { PublisherResponse } from "../../api/types";
import * as actions from "./publisher.actions";
import { StateWithStatus } from "../types";

export type State = StateWithStatus<PublisherResponse[]>;

const initialState = {
  result: [],
  status: "idle",
} as State;

const publisherSlice = createSlice({
  initialState,
  name: "publisherSlice",
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

export const publisher = publisherSlice.reducer;
