import { getFavouriteQueryKey } from '@/app/favourites/hooks/get-favourites-query-key';
import { FavouritePlace } from '@/app/favourites/types';
import { apiService } from '@/app/services/backend-api/api-service';
import { ErrorResponse } from '@/app/services/backend-api/types/error-response';
import { Response } from '@/app/services/backend-api/types/response';
import { useMutation, useQueryClient } from '@tanstack/react-query';
import { AxiosError } from 'axios';

const deleteFavouritePlace = async (accessToken: string, placeId: string) => {
  const { data } = await apiService.delete<Response<FavouritePlace>>(
    `/fav-place/delete/${placeId}`,
    {
      headers: {
        Authorization: `Bearer ${accessToken}`,
      },
    }
  );
  return data;
};

export const useDeleteFavouritePlace = (accessToken: string) => {
  const queryClient = useQueryClient();

  return useMutation<
    Response<FavouritePlace>,
    AxiosError<ErrorResponse>,
    string
  >({
    mutationKey: [...getFavouriteQueryKey(), 'delete'],
    mutationFn: (placeId) => deleteFavouritePlace(accessToken, placeId),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: getFavouriteQueryKey() });
    },
  });
};
