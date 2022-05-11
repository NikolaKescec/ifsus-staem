import { createAsyncThunk } from "@reduxjs/toolkit";

import * as api from "../../../api/articles";

export const findById = createAsyncThunk(
  "articleUpdate/findById",
  api.findById
);
