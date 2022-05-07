import { createSlice } from "@reduxjs/toolkit";

import { GenreResponse } from "../../api/types";
import * as actions from "./genre.actions";
import { StateWithStatus } from "../types";

export type State = StateWithStatus<GenreResponse[]>;

const initialState = {
  result: [],
  status: "idle",
} as State;

const genreSlice = createSlice({
  initialState,
  name: "genreSlice",
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

export const genre = genreSlice.reducer;
