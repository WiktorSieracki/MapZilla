import { getMapPointsQueryKey } from '@/app/homepage/hooks/get-mappoints-query-key';
import { LocateRequest, LocateResponse } from '@/app/homepage/types/locate';
import { apiService } from '@/app/services/backend-api/api-service';
import { Response } from '@/app/services/backend-api/types/response';
import { queryOptions } from '@tanstack/react-query';

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
  location: LocateRequest,
  enabled = true
) =>
  queryOptions<Response<LocateResponse>>({
    queryKey: getMapPointsQueryKey(),
    queryFn: () => fetchMapPoints(accessToken, location),
    enabled: enabled,
  });
