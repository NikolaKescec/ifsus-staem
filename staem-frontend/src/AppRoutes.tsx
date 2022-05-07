import { Route, Routes } from "react-router-dom";

import PrivateRoute from "./components/PrivateRoute";
import ArticleList from "./pages/ArticleList/ArticleList";
import CategoryList from "./pages/CategoryList/CategoryList";
import DeveloperList from "./pages/DeveloperList/DeveloperList";
import GenreList from "./pages/GenreList/GenreList";
import MyGames from "./pages/MyGames/MyGames";
import PublisherList from "./pages/PublisherList/PublisherList";
import NotFound from "./pages/NotFound";
import Profile from "./pages/Profile";

export default function AppRoutes() {
  return (
    <Routes>
      <Route path="/" element={<ArticleList />} />
      <Route element={<PrivateRoute />}>
        <Route path="/my-games" element={<MyGames />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/categories" element={<CategoryList />} />
        <Route path="/developers" element={<DeveloperList />} />
        <Route path="/genres" element={<GenreList />} />
        <Route path="/publishers" element={<PublisherList />} />
      </Route>
      <Route path="*" element={<NotFound />} />
    </Routes>
  );
}
