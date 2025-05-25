import { RefreshTokenResponse } from '@/app/services/user-auth/types/refresh-token-response';
import { User } from '@/app/services/user-auth/types/user';
import axios from 'axios';
import { decodeJwt } from 'jose';
import { Profile } from 'next-auth';
import { JWT } from 'next-auth/jwt';

const calculateTokenExpirationTime = (expiresIn: number) =>
  Math.floor(Date.now() / 1000 + expiresIn);

export const refreshToken = async (token: JWT): Promise<JWT | null> => {
  try {
    const {
      data: {
        id_token,
        access_token,
        expires_in,
        refresh_token,
        refresh_expires_in,
      },
    } = await axios.post<RefreshTokenResponse>(
      process.env.AUTH_KEYCLOAK_TOKEN_URL ||
        'http://keycloak:8080/realms/Mapzilla/protocol/openid-connect/token',
      {
        client_id: process.env.AUTH_KEYCLOAK_ID,
        client_secret: process.env.AUTH_KEYCLOAK_SECRET,
        grant_type: 'refresh_token',
        refresh_token: token.tokens?.refreshToken,
      },
      {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
      }
    );

    const idTokenPayload = id_token ? decodeJwt<Profile>(id_token) : null;
    const user: User | undefined = idTokenPayload
      ? {
          id: idTokenPayload.sub ?? undefined,
          firstName: idTokenPayload.given_name as string | null | undefined,
          lastName: idTokenPayload.family_name as string | null | undefined,
          email: idTokenPayload.email ?? undefined,
        }
      : token.user; // fallback to old properties

    return {
      user,
      tokens: {
        idToken: id_token,
        accessToken: access_token,
        accessTokenExpiresIn: expires_in,
        accessTokenExpiresAt: calculateTokenExpirationTime(expires_in),
        // Fall back to old refresh token
        refreshToken: refresh_token ?? token.tokens?.refreshToken,
        refreshExpiresIn: refresh_expires_in,
      },
    };
    // eslint-disable-next-line @typescript-eslint/no-unused-vars
  } catch (error) {
    // Refresh token expired
    return null;
  }
};
