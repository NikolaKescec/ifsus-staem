import { createAsyncThunk } from "@reduxjs/toolkit";

import * as api from "../../api/articles";

export const findById = createAsyncThunk(
  "articleDetails/findById",
  api.findById
);
