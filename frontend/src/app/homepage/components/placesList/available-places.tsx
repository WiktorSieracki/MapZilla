import { useCenterContext } from '@/app/homepage/context/center-context';
import { Node, Way } from '@/app/homepage/interface/map-objects';
import { Place } from '@/app/homepage/interface/place';
import React from 'react';

interface AvaiablePlacesProps {
  selectedPlaces: Place[];
}

export const AvaiablePlaces = ({ selectedPlaces }: AvaiablePlacesProps) => {
  const { data } = useCenterContext();

  const availablePlaces = selectedPlaces.filter((place) => {
    return data?.elements.some(
      (element: Node | Way) =>
        element.tags?.amenity === place.queryName ||
        element.tags?.leisure === place.queryName
    );
  });

  return (
    <div>
      <h2 className="text-xl font-semibold">Available Places</h2>
      <ul>
        {availablePlaces.map((place) => (
          <li
            key={place.id}
            className="m-2 flex items-center rounded-lg border-2 border-green-600 p-2">
            <span
              className="mr-2 inline-block h-4 w-4 rounded-full"
              style={{ backgroundColor: place.color }}></span>
            {place.readableName}
          </li>
        ))}
      </ul>
    </div>
  );
};
