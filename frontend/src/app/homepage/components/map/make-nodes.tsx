import { Node, Way } from '@/app/homepage/interface/map-objects';
import { places } from '@/app/homepage/interface/place';
import { CircleMarker, Popup } from 'react-leaflet';

interface ElementProps {
  elements: (Node | Way)[];
}

export const MakeNodes = ({ elements }: ElementProps) => {
  const nodes = elements.filter((element) => element.type === 'node');

  const matchNodes = nodes.map((node) => {
    return {
      ...node,
      // temporary id for React key
      id: Math.random().toString(36).substring(7),
      color: places.find(
        (place) => place.queryName === node.tags?.[place.prefix]
      )?.color,
    };
  });

  if (matchNodes.length === 0) {
    return null;
  }

  return (
    <>
      {matchNodes.map((node) => (
        <CircleMarker
          key={node.id}
          center={[node.lat, node.lon]}
          radius={5}
          color={node.color}>
          <Popup>
            {node.tags?.amenity || node.tags?.leisure}
            <br />
            {node.tags?.name}
          </Popup>
        </CircleMarker>
      ))}
    </>
  );
};
