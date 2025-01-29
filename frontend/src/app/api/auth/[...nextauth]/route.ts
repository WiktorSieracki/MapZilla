import NextAuth, { AuthOptions } from "next-auth";
import KeycloakProvider from "next-auth/providers/keycloak";

export const authOptions: AuthOptions = {
  providers: [
    KeycloakProvider({
      clientId: "mapzilla-rest-api",
      clientSecret: "gVYPnTtBDm1d9pRltXeKPvN1dtws6DST", //shouldn't do that, extract later
      issuer: "http://localhost:8080/realms/Mapzilla",
    }),
  ],
};

const handler = NextAuth(authOptions);
export { handler as GET, handler as POST };
