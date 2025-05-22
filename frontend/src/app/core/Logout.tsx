import { signOut } from '@/app/services/user-auth';
import { Button } from '@/components/ui/button';

export default function Logout() {
  return (
    <form
      action={async () => {
        'use server';
        await signOut({
          redirect: true,
          redirectTo: '/',
        });
      }}>
      <Button type="submit">Wyloguj się</Button>
    </form>
  );
}
