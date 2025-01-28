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
      <h2>Available Places</h2>
      <ul>
        {availablePlaces.map((place) => (
          <li key={place.id} className="bg-green-400 p-2 m-2 rounded-lg">
            {place.readableName}
          </li>
        ))}
      </ul>
    </div>
  );
};
