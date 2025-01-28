"use client";

import { useState } from "react";
import { MapBox } from "./components/mapbox";
import { MapPoints } from "./components/mappoints";
import { MultiSelect } from "./components/multi-select";
import { CenterContextProvider } from "./context/center-context";
import { Place } from "./interface/place";
import { PlacesList } from "./components/placesList/places-list";
import { Score } from "./components/placesList/score";
import { places } from "@/app/interface/place";

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
    <div className="flex flex-col items-center justify-center min-h-screen">
      <CenterContextProvider>
        <div className="flex gap-4">
          <div>
            <MapBox />
            <MapPoints places={selectedPlaces} />
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
