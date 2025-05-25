import { getMapPointsQueryOptions } from '@/app/homepage/hooks/get-mappoints-query-options';
import { LocateRequest } from '@/app/homepage/types/locate';
import { useQuery } from '@tanstack/react-query';
export const useFetchMapPoints = (
  accessToken: string,
  location: LocateRequest,
  enabled = true
) => useQuery(getMapPointsQueryOptions(accessToken, location, enabled));
