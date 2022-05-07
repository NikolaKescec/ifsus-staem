import * as React from "react";

import { useAuth0 } from "@auth0/auth0-react";
import {
  Center,
  Container,
  Group,
  Image,
  Paper,
  Stack,
  Text,
} from "@mantine/core";

export default function Profile() {
  const { user } = useAuth0();

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
