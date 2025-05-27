import { QueryClient } from '@tanstack/react-query';
import { getFavouritePlacesQueryOptions } from '@/app/favourites/hooks/get-favourites-query-options';

export const prefetchFavouritePlaces = (
  queryClient: QueryClient,
  accessToken: string
) => queryClient.prefetchQuery(getFavouritePlacesQueryOptions(accessToken));
