"use client";

import { useEffect, useState } from "react";
import { MapPoints } from "./components/mappoints";
import { MultiSelect } from "./components/multi-select";
import { CenterContextProvider } from "./context/center-context";
import { Place } from "./interface/place";
import { PlacesList } from "./components/placesList/places-list";
import { Score } from "./components/placesList/score";
import { places } from "@/app/interface/place";
import dynamic from "next/dynamic";
<<<<<<< HEAD
import { useContext } from "react";
import { getServerSession, Session } from "next-auth";
import { authOptions } from "./api/auth/[...nextauth]/route";
import { LogOut } from "lucide-react";
import Login from "./components/Login";
import Logout from "./components/Logout";
import { GetServerSideProps } from "next";
import { useSession } from "next-auth/react";
// import { AuthContext } from "./context/AuthProvider";

=======
import { Favourite } from "./components/favourite";
>>>>>>> basic-view
const MapBox = dynamic(
  () => import("./components/mapbox").then((mod: any) => mod.default),
  { ssr: false }
);

export default function Home() {
  const [selectedPlaces, setSelectedPlaces] = useState<Place[]>([]);
  // const [session, setSession] = useState<Session | null>(null);
  const { data: session } = useSession();
  // const [session, setSession] = useState(null);
  // const auth = useContext(AuthContext);

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

  // const session = await getServerSession(authOptions);
  if (session) {
    return (
      <div>
        <div>Your name is {session.user?.name}</div>
        <div>
          <Logout />
        </div>
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

  // useEffect(() => {
  //   async function fetchSession() {
  //     const session = await getServerSession(authOptions);
  //     setSession(session);
  //   }
  //   fetchSession();
  // }, []);

  return (
    <div className="flex flex-col justify-center items-center min-h-screen">
      <div>
        <Login />
      </div>
      {/* <div>
        <h1>Keycloak Authentication</h1>
        {auth.isAuthenticated ? (
          <div>
            <p>Welcome, {auth.user?.preferred_username}!</p>
            <button onClick={auth.logout}>Logout</button>
          </div>
        ) : (
          <button onClick={auth.login}>Login</button>
        )}
      </div> */}
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
