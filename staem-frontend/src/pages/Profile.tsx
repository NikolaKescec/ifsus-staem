import * as React from "react";

import { useAuth0 } from "@auth0/auth0-react";

export default function Profile() {
  const { user } = useAuth0();

  return (
    <div className="flex justify-center items-center">
      <div className="text-3xl my-auto">
        <img src={user?.picture} alt="profile" className="h-64 mx-auto my-4" />
        <div>
          Welcome <span className="font-bold">{user?.name}</span>
        </div>
        <div className="my-4 text-xl">
          <div>
            <span className="font-bold">Email: </span>
            {user?.email}
          </div>
          <div>
            <span className="font-bold">Nickname: </span>
            {user?.nickname}
          </div>
        </div>
      </div>
    </div>
  );
}
