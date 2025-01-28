import { useCenterContext } from "@/app/context/center-context";
import { Place } from "@/app/interface/place";
import React from "react";

interface NotAvailablePlacesProps {
  selectedPlaces: Place[];
}

export const NotAvailablePlaces = ({
  selectedPlaces,
}: NotAvailablePlacesProps) => {
  const { data } = useCenterContext();

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

  return (
    <div>
      <h2>Not Available Places</h2>
      <ul>
        {notAvailablePlaces.map((place) => (
          <li key={place.id} className="bg-red-500 p-2 m-2 rounded-lg">
            {place.readableName}
          </li>
        ))}
      </ul>
    </div>
  );
};
