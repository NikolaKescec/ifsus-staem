import { BrowserRouter } from "react-router-dom";

import { Auth0Provider } from "@auth0/auth0-react";

import AppRoutes from "./AppRoutes";
import Navbar from "./components/Navbar";
import { ThemeProvider, useThemeContext } from "./context/ThemeContext";

export default function App() {
  return (
    <Auth0Provider
      domain="sbolsec.eu.auth0.com"
      clientId="D7mA4WjtZfdRclMTCeXqFKocfGDydxbl"
      redirectUri={window.location.origin}
      audience="http://localhost:8080/"
      scope="read:current_user update:current_user_metadata"
    >
      <BrowserRouter>
        <ThemeProvider>
          <UiWrapper />
        </ThemeProvider>
      </BrowserRouter>
    </Auth0Provider>
  );
}

function UiWrapper() {
  const {} = useThemeContext();

  return (
    <div className="flex flex-col h-screen bg-gray-800 text-white">
      <Navbar />
      <main className="flex-grow p-2">
        <AppRoutes />
      </main>
    </div>
  );
}
