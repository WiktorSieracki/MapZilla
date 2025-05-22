import { useQuery } from '@tanstack/react-query';
import { LocateRequest } from '@/app/homepage/types/locate';
import { getMapPointsQueryOptions } from '@/app/homepage/hooks/get-mappoints-query-options';
export const useFetchMapPoints = (
  accessToken: string,
  location: LocateRequest
) => useQuery(getMapPointsQueryOptions(accessToken, location));
