"use client";

import { useSession } from "next-auth/react";
import Login from "./Login";
import Logout from "./Logout";

export const Navbar = () => {
  const { data: session } = useSession();

  return (
    <div className="flex items-center p-2 text-xl w-full bg-red-600 justify-between">
      Mapzilla
      {session ? (
        <div className="flex items-center gap-4">
          {session.user?.name}
          <Logout />
        </div>
      ) : (
        <Login />
      )}
    </div>
  );
};
