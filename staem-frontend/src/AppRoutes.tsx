import { Route, Routes } from "react-router-dom";

import PrivateRoute from "./components/PrivateRoute";
import ArticleDetails from "./pages/ArticleDetails/ArticleDetails";
import ArticleNew from "./pages/ArticleForm/ArticleNew/ArticleNew";
import ArticleList from "./pages/ArticleList/ArticleList";
import CategoryList from "./pages/CategoryList/CategoryList";
import DeveloperList from "./pages/DeveloperList/DeveloperList";
import GenreList from "./pages/GenreList/GenreList";
import MyArticles from "./pages/MyArticles/MyArticles";
import PublisherList from "./pages/PublisherList/PublisherList";
import NotFound from "./pages/NotFound";
import Profile from "./pages/Profile";
import ArticleUpdate from "./pages/ArticleForm/ArticleUpdate/ArticleUpdate";

export default function AppRoutes() {
  return (
    <Routes>
      <Route path="/" element={<ArticleList />} />
      <Route path="/article/new" element={<ArticleNew />} />
      <Route element={<PrivateRoute />}>
        <Route path="/my-articles" element={<MyArticles />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/categories" element={<CategoryList />} />
        <Route path="/developers" element={<DeveloperList />} />
        <Route path="/genres" element={<GenreList />} />
        <Route path="/publishers" element={<PublisherList />} />
      </Route>
      <Route path="/article/:id/update" element={<ArticleUpdate />} />
      <Route path="/article/:id" element={<ArticleDetails />} />
      <Route path="*" element={<NotFound />} />
    </Routes>
  );
}
