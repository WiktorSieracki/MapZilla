import { Place } from '@/app/homepage/interface/place';

export const fetchMapPoints = async (
  center: { x: number; y: number },
  selectedPlaces: Place[]
) => {
  const radius = 0.01;
  const square = `[bbox:${center.x - radius},${center.y - radius},${
    center.x + radius
  },${center.y + radius}]`;

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

  if (!response.ok) {
    throw new Error('Failed to fetch map points');
  }

  return response.json();
};
