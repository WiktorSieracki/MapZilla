import { getFavouriteQueryKey } from '@/app/favourites/hooks/get-favourites-query-key';
import { FavouritePlace } from '@/app/favourites/types';
import { apiService } from '@/app/services/backend-api/api-service';
import { ErrorResponse } from '@/app/services/backend-api/types/error-response';
import { Response } from '@/app/services/backend-api/types/response';
import { getQueryClient } from '@/lib/tanstack-query';
import { useMutation } from '@tanstack/react-query';
import { AxiosError } from 'axios';

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

export const UseCreateFavouritePlace = (accessToken: string) =>
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
