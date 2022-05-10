import React from "react";

import { Provider } from "react-redux";
import { BrowserRouter } from "react-router-dom";

import {
  Affix,
  AppShell,
  Button,
  ColorScheme,
  ColorSchemeProvider,
  MantineProvider,
  Transition,
} from "@mantine/core";
import { useHotkeys, useLocalStorage, useWindowScroll } from "@mantine/hooks";
import { NotificationsProvider } from "@mantine/notifications";
import { IconArrowUp } from "@tabler/icons";

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
  const [colorScheme, setColorScheme] = useLocalStorage<ColorScheme>({
    key: "mantine-color-scheme",
    defaultValue: "dark",
    getInitialValueInEffect: true,
  });

  const toggleColorScheme = (value?: ColorScheme) =>
    setColorScheme(value || (colorScheme === "dark" ? "light" : "dark"));

  useHotkeys([["mod+J", () => toggleColorScheme()]]);

  return (
    <ColorSchemeProvider
      colorScheme={colorScheme}
      toggleColorScheme={toggleColorScheme}
    >
      <MantineProvider theme={{ colorScheme }}>
        <NotificationsProvider position="bottom-left">
          <BrowserRouter>
            <AppLayout />
          </BrowserRouter>
        </NotificationsProvider>
      </MantineProvider>
    </ColorSchemeProvider>
  );
}

function AppLayout() {
  const [scroll, scrollTo] = useWindowScroll();

  return (
    <AppShell
      padding="md"
      fixed={true}
      header={<MyNavbar />}
      style={{ display: "flex", flexDirection: "column", height: "100%" }}
      styles={(theme) => ({
        main: {
          backgroundColor:
            theme.colorScheme === "dark"
              ? theme.colors.dark[8]
              : theme.colors.gray[0],
        },
      })}
    >
      <SharedProvider>
        <AppRoutes />
        <ScrollToTop />
      </SharedProvider>
    </AppShell>
  );
}

function ScrollToTop() {
  const [scroll, scrollTo] = useWindowScroll();

  return (
    <Affix position={{ bottom: 20, right: 20 }}>
      <Transition transition="slide-up" mounted={scroll.y > 0}>
        {(transitionStyles) => (
          <Button
            leftIcon={<IconArrowUp />}
            style={transitionStyles}
            onClick={() => scrollTo({ y: 0 })}
          >
            Scroll to top
          </Button>
        )}
      </Transition>
    </Affix>
  );
}
