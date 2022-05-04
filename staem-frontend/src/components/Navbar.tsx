import { Link, useNavigate } from "react-router-dom";

import { useAuth0 } from "@auth0/auth0-react";

import Button from "./Button";
import LoginButton from "./LoginButton";
import LogoutButton from "./LogoutButton";

export default function Navbar() {
  const { isAuthenticated } = useAuth0();
  const navigate = useNavigate();

  return (
    <nav className="bg-gray-800 py-2 px-4 border-b-2 border-gray-600 flex justify-between space-x-4">
      <div className="space-x-2">
        <Link to="/">
          <Button label="Home" />
        </Link>
        {isAuthenticated && (
          <>
            <Link to="/my-games">
              <Button label="My Games" />
            </Link>
            <Link to="/profile">
              <Button label="Profile" />
            </Link>
          </>
        )}
      </div>
      <div className="flex space-x-2">
        <LoginButton />
        <LogoutButton />
        <UserProfile />
      </div>
    </nav>
  );
}

function UserProfile() {
  const { isAuthenticated, user } = useAuth0();

  if (!isAuthenticated) {
    return null;
  }

  return (
    <div className="flex items-center space-x-2 border border-gray px-2">
      <img src={user?.picture} alt="Profile" className="rounded-3xl h-6" />
      <div>{user?.nickname}</div>
    </div>
  );
}
