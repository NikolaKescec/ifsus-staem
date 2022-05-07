import { createAsyncThunk } from "@reduxjs/toolkit";

import * as api from "../../api/developers";

export const findAll = createAsyncThunk("developers/findAll", api.findAll);
