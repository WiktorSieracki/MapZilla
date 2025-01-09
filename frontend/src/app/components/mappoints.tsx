import React, { useState } from "react";
import { useCenterContext } from "../context/center-context";

export const MapPoints = () => {
  const { center, setData } = useCenterContext();

  const fetchData = async () => {
    try {
      const response = await fetch("https://overpass-api.de/api/interpreter", {
        method: "POST",
        body:
          "data=" +
          encodeURIComponent(`
                          [bbox:54.394,18.574,54.396,18.576]
                          [out:json]
                          [timeout:90]
                          ;
                          node(around:1200, ${center[0]}, ${center[1]})->.center;
                          (
                            node(around.center:1200)["amenity"="place_of_worship"];
                            way(around.center:1200)["amenity"="place_of_worship"];
                            relation(around.center:1200)["amenity"="place_of_worship"];
                            node(around.center:1200)["leisure"="park"];
                            way(around.center:1200)["leisure"="park"];
                            relation(around.center:1200)["leisure"="park"];
                            node(around.center:1200)["amenity"="pharmacy"];
                            way(around.center:1200)["amenity"="pharmacy"];
                            relation(around.center:1200)["amenity"="pharmacy"];
                            );
                            out geom;
                            `),
      });

      const contentType = response.headers.get("content-type");
      if (contentType && contentType.indexOf("application/json") !== -1) {
        const result = await response.json();
        console.log(result);
        setData(result);
      } else {
        const text = await response.text();
        console.error("Unexpected response format:", text);
      }
    } catch (error) {
      console.error("Error fetching data: ", error);
    }
  };

  return (
    <div>
      <button onClick={fetchData}>Fetch Data</button>
    </div>
  );
};
