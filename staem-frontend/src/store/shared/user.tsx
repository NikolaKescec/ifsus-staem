import { createSlice } from "@reduxjs/toolkit";

import { UserPermissionType } from "../../api/types";

export type State = {
  permissions: UserPermissionType[];
};

const initialState = {
  permissions: [],
} as State;

const userSlice = createSlice({
  initialState,
  name: "user",
  reducers: {
    setPermissions: (state, action) => {
      state.permissions = action.payload;
    },
  },
});

export const user = userSlice.reducer;
