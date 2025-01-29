import { Button } from "@/components/ui/button";
import { useCenterContext } from "../context/center-context";
import { Place } from "../interface/place";

interface FavouriteProps {
  lat: number;
  lon: number;
  score: number;
  availablePlaces: string[];
  notAvailablePlaces: string[];
}

export const Favourite = ({ selectedPlaces }: { selectedPlaces: Place[] }) => {
  const { data, locationCenter } = useCenterContext();

  const availablePlaces = selectedPlaces.filter((place) => {
    return data?.elements.some(
      (element: any) =>
        element.tags.amenity === place.queryName ||
        element.tags.leisure === place.queryName
    );
  });

  const notAvailablePlaces = selectedPlaces.filter(
    (place) => !availablePlaces.includes(place)
  );

  const postFavourite = async () => {
    const favourite: FavouriteProps = {
      lat: locationCenter?.x || 0,
      lon: locationCenter?.y || 0,
      score: availablePlaces.length / selectedPlaces.length,
      availablePlaces: availablePlaces.map((place) => place.readableName),
      notAvailablePlaces: notAvailablePlaces.map((place) => place.readableName),
    };
    console.log("Post favourite", favourite);
  };

  return (
    <Button className="m-1" onClick={postFavourite}>
      Add location to Favourites
    </Button>
  );
};
