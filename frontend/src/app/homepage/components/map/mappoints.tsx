import { useCenterContext } from '@/app/homepage/context/center-context';
import { useFetchMapPoints } from '@/app/homepage/hooks/client/use-fetch-mappoints';
import { Place } from '@/app/homepage/interface/place';
import { Button } from '@/components/ui/button';
import { useSession } from 'next-auth/react';

export const MapPoints = ({ selectedPlaces }: { selectedPlaces: Place[] }) => {
  const { center, setData } = useCenterContext();
  const { data: session } = useSession();

  const { refetch, isLoading } = useFetchMapPoints(
    session?.tokens?.accessToken as string,
    {
      lat: center.x,
      lon: center.y,
      radius: 1200,
      types: selectedPlaces.map((place) => place.queryName.toUpperCase()),
    }
  );

  const handleFetch = async () => {
    const result = await refetch();
    if (result.data) {
      setData(result.data);
    }
  };

  return (
    <div>
      <Button
        className="m-1"
        onClick={handleFetch}
        disabled={isLoading}>
        {isLoading ? 'Loading...' : 'Fetch Map Points'}
      </Button>
    </div>
  );
};
