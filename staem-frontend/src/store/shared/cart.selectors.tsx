import { createSelector } from "@reduxjs/toolkit";

import { State } from "./cart";
import { RootState } from "../reducer";

export const items = createSelector(
  (state: RootState) => state.cart,
  (state: State) => state.items
);
