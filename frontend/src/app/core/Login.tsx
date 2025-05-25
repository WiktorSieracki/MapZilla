import { signIn } from '@/app/services/user-auth';
import { Button } from '@/components/ui/button';

export default function Login() {
  return (
    <form
      action={async () => {
        'use server';
        await signIn('keycloak');
      }}>
      <Button
        type="submit"
        variant="secondary">
        Zaloguj się
      </Button>
    </form>
  );
}
