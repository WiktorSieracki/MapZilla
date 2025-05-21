import { Button } from '@/components/ui/button';
import { signOut } from '../services/user-auth';

export default function Logout() {
  return <Button onClick={() => signOut()}>Logout</Button>;
}
