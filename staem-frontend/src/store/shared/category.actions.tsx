import { createAsyncThunk } from "@reduxjs/toolkit";

import * as api from "../../api/categories";

export const findAll = createAsyncThunk("category/findAll", api.findAll);
