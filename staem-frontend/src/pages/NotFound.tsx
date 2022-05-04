import React from "react";

import { Link } from "react-router-dom";

export default function NotFound() {
  return (
    <div className="h-full flex flex-col items-center justify-center">
      <div className="space-y-8 text-center bg-gray-700 p-16 rounded-xl text-xl">
        <p>404! Page not Found</p>
        <p>
          Go to{" "}
          <Link to="/" className="bg-blue-600 py-2 px-4 ml-1 rounded-xl hover:bg-blue-700">
            Homepage
          </Link>
        </p>
      </div>
    </div>
  );
}
