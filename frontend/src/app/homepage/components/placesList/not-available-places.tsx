import { useCenterContext } from '@/app/homepage/context/center-context';
import { Node, Way } from '@/app/homepage/interface/map-objects';
import { Place } from '@/app/homepage/interface/place';
import React from 'react';

interface NotAvailablePlacesProps {
  selectedPlaces: Place[];
}

export const NotAvailablePlaces = ({
  selectedPlaces,
}: NotAvailablePlacesProps) => {
  const { data } = useCenterContext();

  const availablePlaces = selectedPlaces.filter((place) => {
    return data?.data.places.some(
      (element: Node | Way) =>
        element.tags?.amenity === place.queryName ||
        element.tags?.leisure === place.queryName
    );
  });

  const notAvailablePlaces = selectedPlaces.filter(
    (place) => !availablePlaces.includes(place)
  );

  return (
    <div>
      <h2 className="text-xl font-semibold">Not Available Places</h2>
      <ul>
        {notAvailablePlaces.map((place) => (
          <li
            key={place.id}
            className="m-2 rounded-lg bg-red-500 p-2 text-gray-100">
            {place.readableName}
          </li>
        ))}
      </ul>
    </div>
  );
};
