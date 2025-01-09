"use client";

import { MapBox } from "./components/mapbox";
import { MapPoints } from "./components/mappoints";
import { CenterContextProvider } from "./context/center-context";

export default function Home() {
  return (
    <div className="flex flex-col items-center justify-center min-h-screen">
      <h1>Hello</h1>
      <CenterContextProvider>
        <MapBox />
        <MapPoints />
      </CenterContextProvider>
    </div>
  );
}
