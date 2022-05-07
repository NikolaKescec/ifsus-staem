import { createSlice } from "@reduxjs/toolkit";

import {
  CategoryResponse,
  DeveloperResponse,
  GenreResponse,
  PublisherResponse,
  Status,
} from "../../api/types";
import * as actions from "./registry.actions";

export type State = {
  categoryResponse: CategoryResponse[];
  developerResponse: DeveloperResponse[];
  genreResponse: GenreResponse[];
  publisherResponse: PublisherResponse[];
  categoryStatus: Status;
  developerStatus: Status;
  genreStatus: Status;
  publisherStatus: Status;
};

const initialState = {
  categoryStatus: "idle",
  developerStatus: "idle",
  genreStatus: "idle",
  publisherStatus: "idle",
  categoryResponse: [] as CategoryResponse[],
  developerResponse: [] as DeveloperResponse[],
  genreResponse: [] as GenreResponse[],
  publisherResponse: [] as PublisherResponse[],
} as State;

const registrySlice = createSlice({
  initialState,
  name: "registry",
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(actions.getCategories.pending, (state) => {
      state.categoryStatus = "idle";
    });
    builder.addCase(actions.getCategories.fulfilled, (state, action) => {
      state.categoryStatus = "success";
      state.categoryResponse = action.payload;
    });
    builder.addCase(actions.getCategories.rejected, (state) => {
      state.categoryStatus = "error";
    });

    builder.addCase(actions.getDevelopers.pending, (state) => {
      state.developerStatus = "idle";
    });
    builder.addCase(actions.getDevelopers.fulfilled, (state, action) => {
      state.developerStatus = "success";
      state.developerResponse = action.payload;
    });
    builder.addCase(actions.getDevelopers.rejected, (state) => {
      state.developerStatus = "error";
    });

    builder.addCase(actions.getGenres.pending, (state) => {
      state.genreStatus = "idle";
    });
    builder.addCase(actions.getGenres.fulfilled, (state, action) => {
      state.genreStatus = "success";
      state.genreResponse = action.payload;
    });
    builder.addCase(actions.getGenres.rejected, (state) => {
      state.genreStatus = "error";
    });

    builder.addCase(actions.getPublishers.pending, (state) => {
      state.publisherStatus = "idle";
    });
    builder.addCase(actions.getPublishers.fulfilled, (state, action) => {
      state.publisherStatus = "success";
      state.publisherResponse = action.payload;
    });
    builder.addCase(actions.getPublishers.rejected, (state) => {
      state.publisherStatus = "error";
    });
  },
});

export const registry = registrySlice.reducer;
