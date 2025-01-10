import { Polygon, Popup } from "react-leaflet";
import { Node, Way } from "../interface/map-objects";

interface ElementProps {
  elements: (Node | Way)[];
}

export const MakePolygons = ({ elements }: ElementProps) => {
  const ways = elements.filter((element) => element.type === "way");

  return (
    <>
      {ways.map((way: Way) => (
        <Polygon
          key={way.id}
          positions={way.geometry.map((node: { lat: number; lon: number }) => [
            node.lat,
            node.lon,
          ])}
          pathOptions={{ color: "blue" }}
        >
          <Popup>
            {way.tags?.name ||
              way.tags?.amenity ||
              way.tags?.leisure ||
              "No name"}
          </Popup>
        </Polygon>
      ))}
    </>
  );
};
