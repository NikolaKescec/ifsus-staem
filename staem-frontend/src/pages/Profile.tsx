import * as React from "react";

import { Navigate } from "react-router-dom";

import {
  Center,
  Container,
  Group,
  Image,
  Paper,
  Stack,
  Text,
} from "@mantine/core";

import { useAuth0 } from "@auth0/auth0-react";

export default function Profile() {
  const { isAuthenticated, user } = useAuth0();

  if (!isAuthenticated) {
    return <Navigate to="/" />;
  }

  return (
    <Container>
      <Center>
        <Stack>
          <Image src={user?.picture} />
          <Paper p={20}>
            <Group>
              <Text size="xl">Email:</Text>
              <Text size="xl">{user?.email}</Text>
            </Group>
            <Group>
              <Text size="xl">Sub:</Text>
              <Text size="xl">{user?.sub}</Text>
            </Group>
          </Paper>
        </Stack>
      </Center>
    </Container>
  );
}
