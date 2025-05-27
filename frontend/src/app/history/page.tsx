'use client';

import { apiService } from '@/app/services/backend-api/api-service';
import { Response } from '@/app/services/backend-api/types/response';
import { Badge } from '@/components/ui/badge';
import { Card, CardContent, CardHeader } from '@/components/ui/card';
import { useQuery } from '@tanstack/react-query';
import { useSession } from 'next-auth/react';
import { FavouritePlace } from '@/app/favourites/types';

interface HistoryResponse {
  id: string;
  places: FavouritePlace[];
  labels: string[];
}

const fetchHistory = async (accessToken: string) => {
  const { data } = await apiService.get<Response<HistoryResponse>>('/history', {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  });
  return data;
};

const History = () => {
  const { data: session } = useSession();

  const { data: historyData } = useQuery({
    queryKey: ['history'],
    queryFn: () => fetchHistory(session?.tokens?.accessToken as string),
    enabled: !!session?.tokens?.accessToken,
  });

  return (
    <div className="mx-auto max-w-4xl p-6">
      <h1 className="mb-6 text-2xl font-bold">Your Location History</h1>
      <div className="grid gap-4">
        {historyData?.data.places.map((place, index) => (
          <Card key={place.id}>
            <CardHeader>
              <div className="text-lg font-semibold">Location {index + 1}</div>
            </CardHeader>
            <CardContent>
              <div className="space-y-4">
                <p className="text-gray-600">
                  Coordinates: {place.lat.toFixed(4)}, {place.lon.toFixed(4)}
                </p>

                {place.availablePlaces && place.availablePlaces.length > 0 && (
                  <div>
                    <p className="mb-2 font-medium">Available Places Nearby:</p>
                    <div className="flex flex-wrap gap-2">
                      {place.availablePlaces.map((item) => (
                        <Badge
                          key={item}
                          variant="default">
                          {item.toLowerCase().replace('_', ' ')}
                        </Badge>
                      ))}
                    </div>
                  </div>
                )}

                {place.notAvailablePlaces &&
                  place.notAvailablePlaces.length > 0 && (
                    <div>
                      <p className="mb-2 font-medium">Not Available Nearby:</p>
                      <div className="flex flex-wrap gap-2">
                        {place.notAvailablePlaces.map((item) => (
                          <Badge
                            key={item}
                            variant="destructive">
                            {item.toLowerCase().replace('_', ' ')}
                          </Badge>
                        ))}
                      </div>
                    </div>
                  )}

                {place.score && (
                  <div className="mt-2">
                    <p className="font-medium">
                      Location Score: {(place.score * 100).toFixed(0)}%
                    </p>
                  </div>
                )}
              </div>
            </CardContent>
          </Card>
        ))}
      </div>
    </div>
  );
};

export default History;
