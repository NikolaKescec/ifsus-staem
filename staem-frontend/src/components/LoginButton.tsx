import React from "react";

import { Button } from "@mantine/core";

import { useAuth0 } from "@auth0/auth0-react";
import { IconLogin } from "@tabler/icons";

export default function LoginButton() {
  const { loginWithRedirect, isAuthenticated } = useAuth0();

  const onClick = () => {
    loginWithRedirect();
  };

  if (isAuthenticated) {
    return <></>;
  }

  return (
    <Button onClick={onClick} leftIcon={<IconLogin />}>
      Login
    </Button>
  );
}
