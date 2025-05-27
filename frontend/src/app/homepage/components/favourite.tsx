import { UseCreateFavouritePlace } from '@/app/favourites/hooks/client/use-create-favourite-place';
import { useCenterContext } from '@/app/homepage/context/center-context';
import { Place } from '@/app/homepage/interface/place';
import { Button } from '@/components/ui/button';
import { useSession } from 'next-auth/react';

interface FavouriteProps {
  lat: number;
  lon: number;
  score: number;
  availablePlaces: string[];
  notAvailablePlaces: string[];
}

export const Favourite = ({ selectedPlaces }: { selectedPlaces: Place[] }) => {
  const { data, center } = useCenterContext();
  const { data: session } = useSession();
  const { mutate: createFavorite, isPending } = UseCreateFavouritePlace(
    session?.tokens?.accessToken as string
  );

  const availablePlaces = selectedPlaces.filter((place) => {
    return data?.data.places.some(
      (element) =>
        element.tags?.amenity === place.queryName ||
        element.tags?.leisure === place.queryName
    );
  });

  const notAvailablePlaces = selectedPlaces.filter(
    (place) => !availablePlaces.includes(place)
  );

  const postFavourite = async () => {
    const favourite: FavouriteProps = {
      lat: center?.x || 0,
      lon: center?.y || 0,
      score: availablePlaces.length / selectedPlaces.length,
      availablePlaces: availablePlaces.map((place) =>
        place.queryName.toUpperCase()
      ),
      notAvailablePlaces: notAvailablePlaces.map((place) =>
        place.queryName.toUpperCase()
      ),
    };
    await createFavorite(favourite);
    console.log('Post favourite', favourite);
  };

  return (
    <Button
      className="m-1"
      onClick={postFavourite}>
      {isPending ? 'Adding...' : 'Add to Favourites'}
    </Button>
  );
};
