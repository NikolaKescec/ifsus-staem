import React from "react";

import { useAuth0 } from "@auth0/auth0-react";

import { Navigate, Outlet } from "react-router-dom";

export default function PrivateRoute({ children }: { children?: JSX.Element }) {
  const { isAuthenticated } = useAuth0();

  if (!isAuthenticated) {
    return <Navigate to="/" replace />;
  }

  return children ? children : <Outlet />;
}
