import React from "react";

import { Center, Loader } from "@mantine/core";

export default function Spinner() {
  return (
    <Center style={{ height: "100%" }}>
      <Loader />
    </Center>
  );
}
