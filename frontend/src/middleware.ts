import { protectedRoutes } from '@/app-config';
import { NextResponse } from 'next/server';
import { auth } from './app/services/user-auth';

export const middleware = auth(async (request: any) => {
  const { nextUrl, auth: session } = request;
  const currentPathName = nextUrl.pathname;

  const isUserLoggedIn = !!session;
  const isAuthRoute = protectedRoutes.some((route) =>
    currentPathName.toLowerCase().startsWith(route.toLowerCase())
  );

  // auth check
  if (isAuthRoute && !isUserLoggedIn) {
    const signInUrl = nextUrl.clone();
    signInUrl.pathname = '/api/auth/signin';

    const provider = 'keycloak';
    signInUrl.searchParams.set('provider', provider);
    signInUrl.searchParams.set('callbackUrl', currentPathName);

    return NextResponse.redirect(signInUrl);
  }

  return NextResponse.next();
});

export const config = {
  matcher: [
    '/((?!api|_next/static|_next/image|favicon.ico|apple-icon.webp|icons/|images/).*)',
  ],
};
