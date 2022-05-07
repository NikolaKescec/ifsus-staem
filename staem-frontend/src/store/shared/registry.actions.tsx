import { createAsyncThunk } from "@reduxjs/toolkit";

import * as categoriesApi from "../../api/categories";
import * as developersApi from "../../api/developers";
import * as genresApi from "../../api/genres";
import * as publishersApi from "../../api/publishers";

export const getCategories = createAsyncThunk(
  "registry/getCategories",
  categoriesApi.getAll
);

export const getDevelopers = createAsyncThunk(
  "registry/getDevelopers",
  developersApi.getAll
);

export const getGenres = createAsyncThunk(
  "registry/getGenres",
  genresApi.getAll
);

export const getPublishers = createAsyncThunk(
  "registry/getPublishers",
  publishersApi.getAll
);
