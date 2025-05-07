import NextAuth, { AuthOptions } from 'next-auth';
import KeycloakProvider from 'next-auth/providers/keycloak';

const authOptions: AuthOptions = {
  providers: [
    KeycloakProvider({
      jwks_endpoint:
        'http://keycloak:8080/realms/Mapzilla/protocol/openid-connect/certs',
      wellKnown: undefined,
      clientId: process.env.AUTH_KEYCLOAK_ID || 'mapzilla-rest-api',
      clientSecret: process.env.AUTH_KEYCLOAK_SECRET || '**********',
      issuer: 'http://localhost:8080/realms/Mapzilla',
      authorization: {
        params: {
          scope: 'openid profile email',
        },
        url: 'http://localhost:8080/realms/Mapzilla/protocol/openid-connect/auth',
      },
      token:
        'http://keycloak:8080/realms/Mapzilla/protocol/openid-connect/token',
      userinfo:
        'http://keycloak:8080/realms/Mapzilla/protocol/openid-connect/userinfo',
    }),
  ],
  debug: true,
};

const handler = NextAuth(authOptions);
export { handler as GET, handler as POST };
