import { useCenterContext } from '@/app/homepage/context/center-context';
import { Place } from '@/app/homepage/interface/place';
import { Button } from '@/components/ui/button';
import { fetchMapPoints } from '@/hooks/fetch-mappoints';
import { useQuery } from '@tanstack/react-query';

export const MapPoints = ({ selectedPlaces }: { selectedPlaces: Place[] }) => {
  const { center, setData } = useCenterContext();

  const { isLoading, refetch } = useQuery({
    queryKey: ['mapPoints', center],
    queryFn: () => fetchMapPoints(center, selectedPlaces),
    enabled: false,
  });

  const handleFetch = () => {
    refetch().then((result) => {
      setData(result.data);
    });
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
