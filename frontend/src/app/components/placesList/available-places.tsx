import React from "react";
import { useCenterContext } from "../../context/center-context";
import { Place } from "../../interface/place";

interface AvaiablePlacesProps {
  selectedPlaces: Place[];
}

export const AvaiablePlaces = ({ selectedPlaces }: AvaiablePlacesProps) => {
  const { data } = useCenterContext();

  const availablePlaces = selectedPlaces.filter((place) => {
    return data?.elements.some(
      (element: any) =>
        element.tags.amenity === place.queryName ||
        element.tags.leisure === place.queryName
    );
  });

  return (
    <div>
      <h2 className="text-xl font-semibold">Available Places</h2>
      <ul>
        {availablePlaces.map((place) => (
          <li
            key={place.id}
            className="flex items-center p-2 m-2 rounded-lg border-2  border-green-600"
          >
            <span
              className="inline-block w-4 h-4 mr-2 rounded-full"
              style={{ backgroundColor: place.color }}
            ></span>
            {place.readableName}
          </li>
        ))}
      </ul>
    </div>
  );
};
