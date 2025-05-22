import axios from 'axios';
import NextAuth from 'next-auth';
import Keycloak, { KeycloakProfile } from 'next-auth/providers/keycloak';
import { Tokens } from '@/app/services/user-auth/types/tokens';
import { User } from '@/app/services/user-auth/types/user';
import { refreshToken } from '@/app/services/user-auth/utils/refresh-token';

const hasTokenExpired = (expiresAt: number) => Date.now() < expiresAt * 1000;

export const { handlers, signIn, signOut, auth } = NextAuth({
  providers: [
    Keycloak({
      authorization:
        process.env.AUTH_KEYCLOAK_AUTHORIZATION_URL ||
        'http://localhost:8080/realms/Mapzilla/protocol/openid-connect/auth',
      token:
        process.env.AUTH_KEYCLOAK_TOKEN_URL ||
        'http://localhost:8080/realms/Mapzilla/protocol/openid-connect/token',
      clientId: process.env.AUTH_KEYCLOAK_ID || 'mapzilla-rest-api',
      clientSecret: process.env.AUTH_KEYCLOAK_SECRET || '**********',
    }),
  ],
  callbacks: {
    authorized: async ({ auth }) => {
      return !!auth;
    },
    jwt: async ({ token, account, profile, trigger }) => {
      if (trigger === 'update' || trigger === undefined) {
        const refreshed = await refreshToken(token);
        return refreshed ?? token;
      }
      const keycloakProfile = profile as KeycloakProfile;

      if (account && profile) {
        // Save the access token and refresh token in the JWT on the initial login, as well as the user details
        const user: User = {
          id: keycloakProfile.sub,
          firstName: keycloakProfile.given_name,
          lastName: keycloakProfile.family_name,
          email: keycloakProfile.email,
        };

        const tokens: Tokens = {
          idToken: account.id_token,
          accessToken: account.access_token,
          accessTokenExpiresIn: account.expires_in as number,
          accessTokenExpiresAt: account.expires_at,
          refreshToken: account.refresh_token,
          refreshExpiresIn: account.refresh_expires_in as number,
        };

        return { user, tokens };
      } else if (
        token.tokens?.accessTokenExpiresAt &&
        hasTokenExpired(token.tokens.accessTokenExpiresAt)
      ) {
        // If the access token has not expired yet, return it
        return token;
      } else {
        // If the access token has expired, try to refresh it
        const refreshed = await refreshToken(token);
        return refreshed ?? token;
      }
    },
    session: async ({ session, token }) => {
      return { ...session, ...token };
    },
  },
  events: {
    signOut: async (message) => {
      if (!('token' in message) || !message.token) return;

      const session = message.token;

      const url =
        process.env.AUTH_KEYCLOAK_LOGOUT_URL ||
        'http://keycloak:8080/realms/Mapzilla/protocol/openid-connect/logout';
      const params = {
        id_token_hint: session.tokens?.idToken,
      } as const;

      await axios.get(url, { params });
    },
  },
});
