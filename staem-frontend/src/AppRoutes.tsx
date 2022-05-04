import { Route, Routes } from "react-router-dom";
import PrivateRoute from "./components/PrivateRoute";

import GameList from "./pages/GameList/GameList";
import MyGames from "./pages/MyGames/MyGames";
import NotFound from "./pages/NotFound";
import Profile from "./pages/Profile";

export default function AppRoutes() {
  return (
    <Routes>
      <Route path="/" element={<GameList />} />
      <Route element={<PrivateRoute />}>
        <Route path="/my-games" element={<MyGames />} />
        <Route path="/profile" element={<Profile />} />
      </Route>
      <Route path="*" element={<NotFound />} />
    </Routes>
  );
}
