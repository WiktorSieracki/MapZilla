import React from "react";
import { AvaiablePlaces } from "@/app/homepage/components/placesList/available-places";
import { NotAvailablePlaces } from "@/app/homepage/components/placesList/not-available-places";
import { Place } from "@/app/homepage/interface/place";

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
