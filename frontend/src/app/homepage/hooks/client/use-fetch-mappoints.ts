import { useQuery } from '@tanstack/react-query';
import { LocateRequest } from '../../types/locate';
import { getMapPointsQueryOptions } from '../get-mappoints-query-options';
export const useFetchMapPoints = (
  accessToken: string,
  location: LocateRequest
) => useQuery(getMapPointsQueryOptions(accessToken, location));
