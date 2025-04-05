'use client';

import { Favourite } from '@/app/homepage/components/favourite';
import { MapPoints } from '@/app/homepage/components/mappoints';
import { MultiSelect } from '@/app/homepage/components/multi-select';
import { PlacesList } from '@/app/homepage/components/placesList/places-list';
import { Score } from '@/app/homepage/components/placesList/score';
import { CenterContextProvider } from '@/app/homepage/context/center-context';
import { Place, places } from '@/app/homepage/interface/place';
import dynamic from 'next/dynamic';
import { useState } from 'react';

const MapBox = dynamic(
  () => import('@/app/homepage/components/mapbox').then((mod) => mod.default),
  { ssr: false }
);

export default function Home() {
  const [selectedPlaces, setSelectedPlaces] = useState<Place[]>([]);

  const handleValueChange = (selectedValues: string[]) => {
    const selectedPlaces = places.filter((place) =>
      selectedValues.includes(place.readableName)
    );
    setSelectedPlaces(selectedPlaces);
    console.log(selectedPlaces);
  };

  const options = places.map((place) => ({
    value: place.readableName,
    label: place.readableName,
  }));

  return (
    <div className="flex min-h-screen flex-col items-center justify-center">
      <CenterContextProvider>
        <div className="flex gap-4">
          <div>
            <MapBox />
            <MapPoints selectedPlaces={selectedPlaces} />
            <Favourite selectedPlaces={selectedPlaces} />
          </div>
          <div>
            <MultiSelect
              className="w-96"
              options={options}
              onValueChange={handleValueChange}
            />
            <Score selectedPlaces={selectedPlaces} />
            <PlacesList selectedPlaces={selectedPlaces} />
          </div>
        </div>
      </CenterContextProvider>
    </div>
  );
}
