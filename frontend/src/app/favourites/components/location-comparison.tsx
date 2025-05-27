import { ComparisonResult, FavoritePlace } from '@/app/favourites/types';

interface LocationComparisonProps {
  location1: FavoritePlace;
  location2: FavoritePlace;
}

const calculateDistance = (
  lat1: number,
  lon1: number,
  lat2: number,
  lon2: number
): number => {
  const R = 6371; // Earth's radius in km
  const dLat = ((lat2 - lat1) * Math.PI) / 180;
  const dLon = ((lon2 - lon1) * Math.PI) / 180;
  const a =
    Math.sin(dLat / 2) * Math.sin(dLat / 2) +
    Math.cos((lat1 * Math.PI) / 180) *
      Math.cos((lat2 * Math.PI) / 180) *
      Math.sin(dLon / 2) *
      Math.sin(dLon / 2);
  const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
  return R * c;
};

const compareLocations = (
  loc1: FavoritePlace,
  loc2: FavoritePlace
): ComparisonResult => {
  const commonAvailable = loc1.availablePlaces.filter((place) =>
    loc2.availablePlaces.includes(place)
  );
  const commonNotAvailable = loc1.notAvailablePlaces.filter((place) =>
    loc2.notAvailablePlaces.includes(place)
  );
  const uniqueAvailable1 = loc1.availablePlaces.filter(
    (place) => !loc2.availablePlaces.includes(place)
  );
  const uniqueNotAvailable1 = loc1.notAvailablePlaces.filter(
    (place) => !loc2.notAvailablePlaces.includes(place)
  );
  const uniqueAvailable2 = loc2.availablePlaces.filter(
    (place) => !loc1.availablePlaces.includes(place)
  );
  const uniqueNotAvailable2 = loc2.notAvailablePlaces.filter(
    (place) => !loc1.notAvailablePlaces.includes(place)
  );

  return {
    commonAvailable,
    commonNotAvailable,
    uniqueAvailable1,
    uniqueNotAvailable1,
    uniqueAvailable2,
    uniqueNotAvailable2,
    distanceKm: calculateDistance(loc1.lat, loc1.lon, loc2.lat, loc2.lon),
    scoreDifference: Math.abs(loc1.score - loc2.score),
  };
};

export const LocationComparison = ({
  location1,
  location2,
}: LocationComparisonProps) => {
  const comparison = compareLocations(location1, location2);

  return (
    <div className="my-8 rounded-lg bg-white p-6 shadow-lg">
      <h2 className="mb-4 text-xl font-bold">Location Comparison</h2>

      <div className="mb-4">
        <p className="text-lg">
          Distance between locations: {comparison.distanceKm.toFixed(2)} km
        </p>
        <p className="text-lg">
          Score difference: {(comparison.scoreDifference * 100).toFixed(1)}%
        </p>
      </div>

      <div className="grid gap-6 md:grid-cols-2">
        <div>
          <h3 className="mb-2 font-semibold text-green-600">
            Common Available Places
          </h3>
          <ul className="list-inside list-disc">
            {comparison.commonAvailable.map((place, i) => (
              <li
                key={i}
                className="text-gray-700">
                {place}
              </li>
            ))}
          </ul>
        </div>

        <div>
          <h3 className="mb-2 font-semibold text-red-600">
            Common Not Available Places
          </h3>
          <ul className="list-inside list-disc">
            {comparison.commonNotAvailable.map((place, i) => (
              <li
                key={i}
                className="text-gray-700">
                {place}
              </li>
            ))}
          </ul>
        </div>

        <div>
          <div className="space-y-2">
            <div>
              <h4 className="text-green-600">Available:</h4>
              <ul className="list-inside list-disc">
                {comparison.uniqueAvailable1.map((place, i) => (
                  <li
                    key={i}
                    className="text-gray-700">
                    {place}
                  </li>
                ))}
              </ul>
            </div>
            <div>
              <h4 className="text-red-600">Not Available:</h4>
              <ul className="list-inside list-disc">
                {comparison.uniqueNotAvailable1.map((place, i) => (
                  <li
                    key={i}
                    className="text-gray-700">
                    {place}
                  </li>
                ))}
              </ul>
            </div>
          </div>
        </div>

        <div>
          <div className="space-y-2">
            <div>
              <h4 className="text-green-600">Available:</h4>
              <ul className="list-inside list-disc">
                {comparison.uniqueAvailable2.map((place, i) => (
                  <li
                    key={i}
                    className="text-gray-700">
                    {place}
                  </li>
                ))}
              </ul>
            </div>
            <div>
              <h4 className="text-red-600">Not Available:</h4>
              <ul className="list-inside list-disc">
                {comparison.uniqueNotAvailable2.map((place, i) => (
                  <li
                    key={i}
                    className="text-gray-700">
                    {place}
                  </li>
                ))}
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
