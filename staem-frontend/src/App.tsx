import { Provider } from "react-redux";
import { BrowserRouter } from "react-router-dom";

import { Auth0Provider } from "@auth0/auth0-react";
import cx from "classnames";

import AppRoutes from "./AppRoutes";
import Navbar from "./components/Navbar";
import { ThemeProvider, useThemeContext } from "./context/ThemeContext";
import store from "./store/store";

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
    <ThemeProvider>
      <BrowserRouter>
        <AppLayout />
      </BrowserRouter>
    </ThemeProvider>
  );
}

function AppLayout() {
  const { body } = useThemeContext();

  const bodyClassName = cx({
    [body]: true,
    "flex flex-col h-screen": true,
  });

  return (
    <div className={bodyClassName}>
      <Navbar />
      <main className="flex-grow p-2">
        <AppRoutes />
      </main>
    </div>
  );
}
