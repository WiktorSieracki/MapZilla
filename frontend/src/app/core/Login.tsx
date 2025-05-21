'use client';
import { Button } from '@/components/ui/button';
import { signIn } from '../services/user-auth';

export default function Login() {
  return <Button onClick={() => signIn()}>Login</Button>;
}
