import { getFavouriteQueryKey } from '@/app/favourites/hooks/get-favourites-query-key';
import { FavouritePlace } from '@/app/favourites/types';
import { apiService } from '@/app/services/backend-api/api-service';
import { Response } from '@/app/services/backend-api/types/response';
import { useMutation, useQueryClient } from '@tanstack/react-query';

interface UpdateFavouritePlaceDto {
  score?: number;
  lat: number;
  lon: number;
  availablePlaces: string[];
  notAvailablePlaces: string[];
  labels: string[];
}

export const useUpdateFavouritePlace = (accessToken: string) => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: async ({
      placeId,
      data,
    }: {
      placeId: string;
      data: UpdateFavouritePlaceDto;
    }) => {
      const response = await apiService.patch<Response<FavouritePlace>>(
        `/fav-place/${placeId}`,
        data,
        {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        }
      );
      return response.data;
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: getFavouriteQueryKey() });
    },
  });
};
