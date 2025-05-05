import NextAuth, { AuthOptions } from 'next-auth';
import KeycloakProvider from 'next-auth/providers/keycloak';

const authOptions: AuthOptions = {
  providers: [
    KeycloakProvider({
      clientId: 'mapzilla-rest-api',
      clientSecret: '**********', //shouldn't do that, extract later
      issuer: 'http://keycloak:8080/realms/Mapzilla',
    }),
  ],
};

const handler = NextAuth(authOptions);
export { handler as GET, handler as POST };
