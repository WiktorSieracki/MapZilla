'use client';

import Login from '@/app/core/Login';
import Logout from '@/app/core/Logout';
import { useSession } from 'next-auth/react';

export const Navbar = () => {
  const { data: session } = useSession();

  return (
    <div className="flex w-full items-center justify-between bg-red-600 p-2 text-xl">
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
