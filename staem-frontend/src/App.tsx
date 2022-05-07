import React from "react";

import { Provider } from "react-redux";
import { BrowserRouter } from "react-router-dom";

import { AppShell, MantineProvider } from "@mantine/core";

import { Auth0Provider } from "@auth0/auth0-react";

import AppRoutes from "./AppRoutes";
import MyNavbar from "./components/MyNavbar";
import store from "./store/store";
import SharedProvider from "./components/SharedProvider";

export default function App() {
  return (
    <Provider store={store}>
      <Auth0Provider
        domain="sbolsec.eu.auth0.com"
        clientId="D7mA4WjtZfdRclMTCeXqFKocfGDydxbl"
        redirectUri={window.location.origin}
        audience="http://localhost:8080/"
        scope="read:current_user update:current_user_metadata"
      >
        <UiProvider />
      </Auth0Provider>
    </Provider>
  );
}

function UiProvider() {
  return (
    <MantineProvider theme={{ colorScheme: "dark" }}>
      <BrowserRouter>
        <AppLayout />
      </BrowserRouter>
    </MantineProvider>
  );
}

function AppLayout() {
  return (
    <AppShell
      padding="md"
      fixed={true}
      header={<MyNavbar />}
      style={{ display: "flex", flexDirection: "column", height: "100%" }}
      styles={(theme) => ({
        body: {
          display: "flex",
          flexDirection: "column",
        },
        main: {
          flexGrow: 3,
          backgroundColor:
            theme.colorScheme === "dark"
              ? theme.colors.dark[8]
              : theme.colors.gray[0],
        },
      })}
    >
      <SharedProvider>
        <AppRoutes />
      </SharedProvider>
    </AppShell>
  );
}
