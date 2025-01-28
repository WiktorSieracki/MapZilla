"use client";

import { useState } from "react";
import { MapBox } from "./components/mapbox";
import { MapPoints } from "./components/mappoints";
import { MultiSelect } from "./components/multi-select";
import { CenterContextProvider } from "./context/center-context";
import { Place } from "./interface/place";
import { PlacesList } from "./components/placesLIst/places-list";

export default function Home() {
  const places: Place[] = [
    {
      id: 1,
      prefix: "amenity",
      queryName: "place_of_worship",
      readableName: "Place of Worship",
      color: "red",
    },
    {
      id: 2,
      prefix: "leisure",
      queryName: "park",
      readableName: "Park",
      color: "green",
    },
    {
      id: 3,
      prefix: "amenity",
      queryName: "pharmacy",
      readableName: "Pharmacy",
      color: "blue",
    },
  ];

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
            <PlacesList selectedPlaces={selectedPlaces} />
          </div>
        </div>
      </CenterContextProvider>
    </div>
  );
}
