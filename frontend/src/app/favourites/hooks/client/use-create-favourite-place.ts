import { apiService } from '@/app/services/backend-api/api-service';
import { ErrorResponse } from '@/app/services/backend-api/types/error-response';
import { Response } from '@/app/services/backend-api/types/response';
import { getQueryClient } from '@/lib/tanstack-query';
import { useMutation } from '@tanstack/react-query';
import { AxiosError } from 'axios';
import { FavouritePlace } from '../../types';
import { getFavouriteQueryKey } from '../get-favourites-query-key';

interface FavoritePlacePayload {
  score: number;
  lat: number;
  lon: number;
  availablePlaces: string[];
  notAvailablePlaces: string[];
}

const createFavouritePlace = async (
  accessToken: string,
  favouritePlace: FavoritePlacePayload
) => {
  const { data } = await apiService.post<Response<FavouritePlace>>(
    `/fav-place`,
    favouritePlace,
    {
      headers: {
        Authorization: `Bearer ${accessToken}`,
      },
    }
  );
  return data;
};

export const usecreateFavouritePlace = (accessToken: string) =>
  useMutation<
    Response<FavouritePlace>,
    AxiosError<ErrorResponse>,
    FavoritePlacePayload
  >({
    mutationKey: [...getFavouriteQueryKey(), 'create'],
    mutationFn: (favouritePlace) =>
      createFavouritePlace(accessToken, favouritePlace),

    onSuccess: () => {
      const queryClient = getQueryClient();
      const favouriteQueryKey = getFavouriteQueryKey();

      queryClient.invalidateQueries({
        queryKey: favouriteQueryKey,
      });
    },
  });
