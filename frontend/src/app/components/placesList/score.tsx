"use client";

import React from "react";
import { Place } from "@/app/interface/place";
import { useCenterContext } from "@/app/context/center-context";

interface ScorePlaces {
  selectedPlaces: Place[];
}

export const Score = ({ selectedPlaces }: ScorePlaces) => {
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
      <h2 className="text-xl font-semibold">Score</h2>
      <div className="bg-blue-600 m-2 p-2 rounded-lg text-white w-max text-center">
        {availablePlaces.length} / {selectedPlaces.length}
      </div>
    </div>
  );
};
