import { getFavouriteQueryKey } from '@/app/favourites/hooks/get-favourites-query-key';
import { FavoritePlace } from '@/app/favourites/types';
import { apiService } from '@/app/services/backend-api/api-service';
import { ErrorResponse } from '@/app/services/backend-api/types/error-response';
import { Response } from '@/app/services/backend-api/types/response';
import { queryOptions } from '@tanstack/react-query';
import { AxiosError } from 'axios';

const fetchFavouritePlaces = async (accessToken: string) => {
  const { data } = await apiService.get<Response<FavoritePlace[]>>(
    '/fav-place',
    {
      headers: {
        Authorization: `Bearer ${accessToken}`,
      },
    }
  );
  return data;
};

export const getFavouritePlacesQueryOptions = (accessToken: string) =>
  queryOptions<Response<FavoritePlace[]>, AxiosError<ErrorResponse>>({
    queryKey: [...getFavouriteQueryKey()],
    queryFn: () => fetchFavouritePlaces(accessToken),
  });
