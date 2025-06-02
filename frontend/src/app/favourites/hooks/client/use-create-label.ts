import { useMutation, useQueryClient } from '@tanstack/react-query';
import axios from 'axios';
import { getLabelsQueryKey } from '../get-labels-query-key';

interface CreateLabelDto {
  name: string;
  color: string;
}

interface Label {
  id: string;
  name: string;
  color: string;
}

interface ApiResponse<T> {
  code: string;
  message: string;
  data: T;
}

export const useCreateLabel = (token: string) => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: async (data: CreateLabelDto) => {
      const response = await axios.post<ApiResponse<Label>>('/labels', data, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      return response.data;
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: getLabelsQueryKey() });
    },
  });
};
