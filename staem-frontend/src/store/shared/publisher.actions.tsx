import { createAsyncThunk } from "@reduxjs/toolkit";

import * as api from "../../api/publishers";

export const findAll = createAsyncThunk("publisher/findAll", api.findAll);
