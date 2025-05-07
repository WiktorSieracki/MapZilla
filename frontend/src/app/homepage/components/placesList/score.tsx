'use client';

import { useCenterContext } from '@/app/homepage/context/center-context';
import { Node, Way } from '@/app/homepage/interface/map-objects';
import { Place } from '@/app/homepage/interface/place';
import React from 'react';

interface ScorePlaces {
  selectedPlaces: Place[];
}

export const Score = ({ selectedPlaces }: ScorePlaces) => {
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
      <h2 className="text-xl font-semibold">Score</h2>
      <div className="m-2 w-max rounded-lg bg-blue-600 p-2 text-center text-white">
        {availablePlaces.length} / {selectedPlaces.length}
      </div>
    </div>
  );
};
