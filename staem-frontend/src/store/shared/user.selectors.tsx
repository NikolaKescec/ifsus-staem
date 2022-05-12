import { createSelector } from "@reduxjs/toolkit";

import { State } from "./user";
import { RootState } from "../reducer";

export const permissions = createSelector(
  (state: RootState) => state.user,
  (state: State) => state.permissions
);
