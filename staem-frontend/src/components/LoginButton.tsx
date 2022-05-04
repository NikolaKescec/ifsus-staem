import React from "react";

import { useAuth0 } from "@auth0/auth0-react";

import Button from "./Button";

export default function LoginButton() {
  const { loginWithRedirect, isAuthenticated } = useAuth0();

  const onClick = () => {
    loginWithRedirect();
  };

  if (isAuthenticated) {
    return <></>;
  }

  return <Button label="Log in" variant="primary" onClick={onClick} />;
}
