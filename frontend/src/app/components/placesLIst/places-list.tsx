import React from "react";
import { AvaiablePlaces } from "./available-places";
import { NotAvailablePlaces } from "./not-available-places";
import { Place } from "../../interface/place";

interface PlacesListProps {
  selectedPlaces: Place[];
}

export const PlacesList = ({ selectedPlaces }: PlacesListProps) => {
  return (
    <ul>
      <AvaiablePlaces selectedPlaces={selectedPlaces} />
      <NotAvailablePlaces selectedPlaces={selectedPlaces} />
    </ul>
  );
};
