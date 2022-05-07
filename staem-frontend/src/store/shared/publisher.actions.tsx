import { createAsyncThunk } from "@reduxjs/toolkit";

import * as api from "../../api/categories";

export const findAll = createAsyncThunk("publisher/findAll", api.findAll);
