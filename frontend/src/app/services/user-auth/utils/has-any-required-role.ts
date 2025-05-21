import { UserRole } from '@/core/services/user-auth/enums/user-role';
import { Session } from 'next-auth';

export const hasAnyRequiredRole = (
  session: Session | null,
  roles: UserRole[]
) => {
  if (!session?.user?.roles) return false;

  const userRolesSet = new Set(
    session.user.roles.map((role) => role.toLowerCase())
  );
  return roles.some((role) => userRolesSet.has(role.toLowerCase()));
};
