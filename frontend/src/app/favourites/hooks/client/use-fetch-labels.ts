import { useQuery } from '@tanstack/react-query';
import axios from 'axios';
import { getLabelsQueryKey } from '../get-labels-query-key';

export interface Label {
  id: string;
  name: string;
  color: string;
}

interface ApiResponse<T> {
  code: string;
  message: string;
  data: T;
}

export const useFetchLabels = (token: string) => {
  return useQuery({
    queryKey: getLabelsQueryKey(),
    queryFn: async () => {
      const response = await axios.get<ApiResponse<Label[]>>('/labels', {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      return response.data;
    },
    enabled: !!token,
  });
};
