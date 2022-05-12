import { createSlice } from "@reduxjs/toolkit";

import { ArticleResponse } from "../../api/types";

export type State = {
  items: ArticleResponse[];
};

const initialState = {
  items: [],
} as State;

const cartSlice = createSlice({
  initialState,
  name: "cart",
  reducers: {
    addItem: (state, action) => {
      if (!state.items.find((item) => item.id === action.payload.id)) {
        state.items = [...state.items, action.payload];
      }
    },
    clear: (state) => {
      state.items = [];
    },
    removeItem: (state, action) => {
      state.items = state.items.filter((item) => item.id !== action.payload);
    },
  },
});

export const cart = cartSlice.reducer;
