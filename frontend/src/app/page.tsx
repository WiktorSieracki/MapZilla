"use client";

import { MapBox } from "./components/mapbox";
import { MapPoints } from "./components/mappoints";
import { CenterContextProvider } from "./context/center-context";
import { Place } from "./interface/places";

export default function Home() {
  const places: Place[] = [
    '"amenity" = "place_of_worship"',
    '"leisure" = "park"',
    '"amenity" = "pharmacy"',
  ];

  return (
    <div className="flex flex-col items-center justify-center min-h-screen">
      <h1>Hello</h1>
      <CenterContextProvider>
        <MapBox />
        <MapPoints places={places} />
      </CenterContextProvider>
    </div>
  );
}
