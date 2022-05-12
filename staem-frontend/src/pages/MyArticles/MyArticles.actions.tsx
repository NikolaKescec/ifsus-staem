import { createAsyncThunk } from "@reduxjs/toolkit";

import * as api from "../../api/articles";

export const findArticles = createAsyncThunk(
  "myArticles/findArticles",
  api.findBought
);
