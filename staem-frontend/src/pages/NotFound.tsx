import React from "react";

import { useNavigate } from "react-router-dom";

import { Alert, Button, Card, Center } from "@mantine/core";
import { IconAlertCircle } from "@tabler/icons";

export default function NotFound() {
  const navigate = useNavigate();

  return (
    <Center>
      <Card>
        <Card.Section p={10}>
          <Alert icon={<IconAlertCircle />} title="Error!" color="red">
            404! Page not Found!
          </Alert>
        </Card.Section>
        <Card.Section p={10}>
          <Center>
            <Button size="lg" onClick={() => navigate("/")}>
              Go to Homepage
            </Button>
          </Center>
        </Card.Section>
      </Card>
    </Center>
  );
}
