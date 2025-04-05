import { useCenterContext } from '@/app/homepage/context/center-context';
import { Place } from '@/app/homepage/interface/place';
import { Button } from '@/components/ui/button';

export const MapPoints = ({ selectedPlaces }: { selectedPlaces: Place[] }) => {
  const { center, setData, setLocationCenter } = useCenterContext();

  const fetchData = async () => {
    const radius = 0.01;
    setLocationCenter?.({ x: center.x, y: center.y });
    const square = `[bbox:${center.x - radius},${center.y - radius},${
      center.x + radius
    },${center.y + radius}]`;
    try {
      const response = await fetch('https://overpass-api.de/api/interpreter', {
        method: 'POST',
        body:
          'data=' +
          encodeURIComponent(`
                          ${square}
                          [out:json]
                          [timeout:90]
                          ;
                          node(around:1200, ${center.x}, ${center.y})->.center;
                          (
                            ${selectedPlaces
                              .map(
                                (place) =>
                                  `node(around.center:1200)["${place.prefix}" = "${place.queryName}"];way(around.center:1200)["${place.prefix}" = "${place.queryName}"];relation(around.center:1200)["${place.prefix}" = "${place.queryName}"];`
                              )
                              .join('')}
                            );
                            out geom;
                            `),
      });

      const result = await response.json();
      console.log(result);
      setData(result);
    } catch (error) {
      console.error('Error fetching data: ', error);
    }
  };

  return (
    <div>
      <Button
        className="m-1"
        onClick={fetchData}>
        Find Locations
      </Button>
    </div>
  );
};
