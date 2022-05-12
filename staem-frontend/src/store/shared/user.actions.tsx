import { createAction } from "@reduxjs/toolkit";

import { UserPermissionType } from "../../api/types";

export const setPermissions = createAction<UserPermissionType[]>(
  "user/setPermissions"
);
