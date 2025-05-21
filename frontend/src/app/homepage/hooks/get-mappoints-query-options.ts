import { apiService } from '@/app/services/backend-api/api-service';
import { Response } from '@/app/services/backend-api/types/response';
import { queryOptions } from '@tanstack/react-query';
import { LocateRequest, LocateResponse } from '../types/locate';
import { getMapPointsQueryKey } from './get-mappoints-query-key';

const fetchMapPoints = async (accessToken: string, location: LocateRequest) => {
  const { data } = await apiService.post<Response<LocateResponse>>(
    `/map/locate`,
    location,
    {
      headers: {
        Authorization: `Bearer ${accessToken}`,
      },
    }
  );
  return data;
};

export const getMapPointsQueryOptions = (
  accessToken: string,
  location: LocateRequest
) =>
  queryOptions<Response<LocateResponse>>({
    // eslint-disable-next-line @tanstack/query/exhaustive-deps
    queryKey: getMapPointsQueryKey(),
    queryFn: () => fetchMapPoints(accessToken, location),
  });
