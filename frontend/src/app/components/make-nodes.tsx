import { CircleMarker, Popup } from "react-leaflet";
import { Node, Way } from "../interface/map-objects";

interface ElementProps {
  elements: (Node | Way)[];
}

export const MakeNodes = ({ elements }: ElementProps) => {
  const nodes = elements.filter((element) => element.type === "node");

  return (
    <>
      {nodes.map((node: Node) => (
        <CircleMarker
          key={node.id}
          center={[node.lat, node.lon]}
          radius={5}
          color="red"
        >
          <Popup>
            {node.tags?.name ||
              node.tags?.amenity ||
              node.tags?.leisure ||
              "No name"}
          </Popup>
        </CircleMarker>
      ))}
    </>
  );
};
