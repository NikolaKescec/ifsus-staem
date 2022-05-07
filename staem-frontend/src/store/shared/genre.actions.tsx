import { createAsyncThunk } from "@reduxjs/toolkit";

import * as api from "../../api/genres";

export const findAll = createAsyncThunk("genres/findAll", api.findAll);
