import Login from '@/app/core/Login';
import Logout from '@/app/core/Logout';
import { auth } from '@/app/services/user-auth';
import Link from 'next/link';

export const Navbar = async () => {
  const session = await auth();

  return (
    <div className="flex w-full items-center justify-between bg-[#79745C] p-2 text-xl">
      <Link
        className="font-semibold"
        href={'/'}>
        Mapzilla
      </Link>
      {session ? (
        <div className="flex items-center gap-4 font-semibold">
          <Link href={'/favourites'}>Favorites</Link>
          <Link href={'/history'}>History</Link>
          <Link href={'/favourites'}>{session.user?.firstName}</Link>
          <Logout />
        </div>
      ) : (
        <Login />
      )}
    </div>
  );
};
