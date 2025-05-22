import { Node, Way } from '@/app/homepage/interface/map-objects';
import { places } from '@/app/homepage/interface/place';
import { Polygon, Popup } from 'react-leaflet';

interface ElementProps {
  elements: (Node | Way)[];
}

export const MakePolygons = ({ elements }: ElementProps) => {
  const ways = elements.filter((element) => element.type === 'way');

  const matchedWays = ways.map((way) => {
    return {
      ...way,
      color: places.find(
        (place) => place.queryName === way.tags?.[place.prefix]
      )?.color,
    };
  });

  return (
    <>
      {matchedWays.map((way) => (
        <Polygon
          key={way.id}
          positions={way.geometry?.map((node: { lat: number; lon: number }) => [
            node.lat,
            node.lon,
          ])}
          pathOptions={{ color: way.color }}>
          <Popup>
            {way.tags?.amenity || way.tags?.leisure}
            <br />
            {way.tags?.name}
          </Popup>
        </Polygon>
      ))}
    </>
  );
};
