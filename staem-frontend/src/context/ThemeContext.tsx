import React from "react";

type ThemeType = {
  body: string;
  button: {
    primary: string;
    secondary: string;
  };
};

const initialTheme: ThemeType = {
  body: "bg-gray-800 text-white",
  button: {
    primary: "bg-gray-700 py-2 px-4 hover:bg-gray-600",
    secondary: "",
  },
};

const ThemeContext = React.createContext<ThemeType>(initialTheme);

export function useThemeContext() {
  return React.useContext(ThemeContext);
}

export function ThemeProvider({ children }: { children: React.ReactNode }) {
  return (
    <ThemeContext.Provider value={initialTheme}>
      {children}
    </ThemeContext.Provider>
  );
}
