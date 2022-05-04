import React from "react";

import { useAuth0 } from "@auth0/auth0-react";

import Button from "./Button";

export default function LogoutButton() {
  const { logout, isAuthenticated } = useAuth0();

  const onClick = () => {
    logout({ returnTo: window.location.origin });
  };

  if (!isAuthenticated) {
    return <></>;
  }

  return <Button label="Log Out" onClick={onClick} />;
}
