'use client';

import Login from '@/app/core/Login';
import Logout from '@/app/core/Logout';
import { useSession } from 'next-auth/react';
import Link from 'next/link';

export const Navbar = () => {
  const { data: session } = useSession();

  return (
    <div className="flex w-full items-center justify-between bg-[#79745C] p-2 text-xl">
      <Link
        className="font-semibold"
        href={'/'}>
        Mapzilla
      </Link>
      {session ? (
        <div className="flex items-center gap-4 font-semibold">
          <Link href={'/favourites'}>{session.user?.firstName}</Link>
          <Logout />
        </div>
      ) : (
        <Login />
      )}
    </div>
  );
};
