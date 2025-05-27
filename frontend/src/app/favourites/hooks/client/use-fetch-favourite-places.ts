import { useQuery } from '@tanstack/react-query';
import { getFavouritePlacesQueryOptions } from '@/app/favourites/hooks/get-favourites-query-options';

export const useFetchFavouritePlaces = (accessToken: string) =>
  useQuery(getFavouritePlacesQueryOptions(accessToken));
