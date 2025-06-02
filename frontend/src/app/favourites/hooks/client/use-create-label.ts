import { apiService } from '@/app/services/backend-api/api-service';
import { Response } from '@/app/services/backend-api/types/response';
import { useMutation, useQueryClient } from '@tanstack/react-query';
import { getLabelsQueryKey } from '@/app/favourites/hooks/get-labels-query-key';

interface CreateLabelDto {
  name: string;
  color: string;
}

interface Label {
  id: string;
  name: string;
  color: string;
}

export const useCreateLabel = (token: string) => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: async (data: CreateLabelDto) => {
      const response = await apiService.post<Response<Label>>('/labels', data, {
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
