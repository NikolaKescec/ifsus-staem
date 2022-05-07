import { createSelector } from "@reduxjs/toolkit";

import { State } from "./registry";
import { RootState } from "../reducer";

export const categoryStatus = createSelector(
  (state: RootState) => state.registry,
  (state: State) => state.categoryStatus
);

export const developerStatus = createSelector(
  (state: RootState) => state.registry,
  (state: State) => state.developerStatus
);

export const genreStatus = createSelector(
  (state: RootState) => state.registry,
  (state: State) => state.genreStatus
);

export const publisherStatus = createSelector(
  (state: RootState) => state.registry,
  (state: State) => state.publisherStatus
);

export const categories = createSelector(
  (state: RootState) => state.registry,
  (state: State) => {
    if (state.categoryStatus === "success") {
      return state.categoryResponse;
    } else {
      throw new Error("Categories not loaded");
    }
  }
);

export const developers = createSelector(
  (state: RootState) => state.registry,
  (state: State) => {
    if (state.developerStatus === "success") {
      return state.developerResponse;
    } else {
      throw new Error("Developers not loaded");
    }
  }
);

export const genres = createSelector(
  (state: RootState) => state.registry,
  (state: State) => {
    if (state.genreStatus === "success") {
      return state.genreResponse;
    } else {
      throw new Error("Genres not loaded");
    }
  }
);

export const publishers = createSelector(
  (state: RootState) => state.registry,
  (state: State) => {
    if (state.publisherStatus === "success") {
      return state.publisherResponse;
    } else {
      throw new Error("Publishers not loaded");
    }
  }
);
