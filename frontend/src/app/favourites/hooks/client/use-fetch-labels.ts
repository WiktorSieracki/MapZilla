import { apiService } from '@/app/services/backend-api/api-service';
import { Response } from '@/app/services/backend-api/types/response';
import { useQuery } from '@tanstack/react-query';
import { getLabelsQueryKey } from '../get-labels-query-key';

export interface Label {
  id: string;
  name: string;
  color: string;
}

export const useFetchLabels = (token: string) => {
  return useQuery({
    queryKey: getLabelsQueryKey(),
    queryFn: async () => {
      const response = await apiService.get<Response<Label[]>>('/labels', {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      return response.data;
    },
    enabled: !!token,
  });
};
