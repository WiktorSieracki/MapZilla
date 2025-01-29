"use client";

import { useState } from "react";
import { MapPoints } from "./components/mappoints";
import { MultiSelect } from "./components/multi-select";
import { CenterContextProvider } from "./context/center-context";
import { Place } from "./interface/place";
import { PlacesList } from "./components/placesList/places-list";
import { Score } from "./components/placesList/score";
import { places } from "@/app/interface/place";
import dynamic from "next/dynamic";
import Login from "./components/Login";
import Logout from "./components/Logout";
import { useSession } from "next-auth/react";
import { Favourite } from "./components/favourite";

const MapBox = dynamic(
  () => import("./components/mapbox").then((mod: any) => mod.default),
  { ssr: false }
);

export default function Home() {
  const [selectedPlaces, setSelectedPlaces] = useState<Place[]>([]);
  const { data: session } = useSession();

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

  if (session) {
    return (
      <div className="flex flex-col justify-center items-center min-h-screen">
        <div>Your name is {session.user?.name}</div>
        <div>
          <Logout />
        </div>
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

  return (
    <div className="flex flex-col justify-center items-center min-h-screen">
      <div>
        <Login />
      </div>
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
