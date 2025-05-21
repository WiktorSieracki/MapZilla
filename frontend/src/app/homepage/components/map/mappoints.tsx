import { useCenterContext } from '@/app/homepage/context/center-context';
import { Place } from '@/app/homepage/interface/place';
import { Button } from '@/components/ui/button';
import { useFetchMapPoints } from '../../hooks/client/use-fetch-mappoints';

export const MapPoints = ({ selectedPlaces }: { selectedPlaces: Place[] }) => {
  const { center, setData } = useCenterContext();

  const { refetch, isLoading } = useFetchMapPoints({
    lat: center.x,
    lon: center.y,
    radius: 1200,
    types: selectedPlaces.map((place) => place.queryName.toUpperCase()),
  });

  const handleFetch = async () => {
    const result = await refetch();
    if (result.data) {
      setData(result.data.data);
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
