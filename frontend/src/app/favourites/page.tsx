'use client';

interface FavoritePlace {
  score: number;
  lat: number;
  lon: number;
  availablePlaces: string[];
  notAvailablePlaces: string[];
}

const Favourites = () => {
  // For now using mock data, later we'll fetch from API
  const favorites: FavoritePlace[] = [
    {
      score: 0.5,
      lat: 52.2298,
      lon: 21.0122,
      availablePlaces: ['Park', 'Kawiarnia'],
      notAvailablePlaces: ['Szpital', 'Lotnisko'],
    },
    {
      score: 0.5,
      lat: 41.8919,
      lon: 12.5113,
      availablePlaces: ['Restauracja', 'Sklep'],
      notAvailablePlaces: ['Szkoła', 'Stadion'],
    },
    {
      score: 0.5,
      lat: 48.8566,
      lon: 2.3522,
      availablePlaces: ['Muzeum', 'Teatr'],
      notAvailablePlaces: ['Basen', 'Hala sportowa'],
    },
    {
      score: 0.5,
      lat: 51.5074,
      lon: -0.1278,
      availablePlaces: ['Biblioteka', 'Galeria'],
      notAvailablePlaces: ['Dworzec', 'Lotnisko'],
    },
  ];

  return (
    <div className="mx-auto max-w-4xl p-6">
      <h1 className="mb-6 text-2xl font-bold">Your Favorite Locations</h1>
      <div className="grid gap-4">
        {favorites.map((place, index) => (
          <div
            key={index}
            className="rounded-lg bg-white p-4 shadow-md">
            <div className="mb-4 flex items-start justify-between">
              <div>
                <h2 className="mb-2 text-xl font-semibold">
                  Location {index + 1}
                </h2>
                <p className="text-gray-600">
                  Coordinates: {place.lat}, {place.lon}
                </p>
              </div>
              <div className="rounded bg-blue-100 px-3 py-1 font-medium text-blue-800">
                Score: {Math.round(place.score * 100)}%
              </div>
            </div>

            <div className="grid gap-4 md:grid-cols-2">
              <div>
                <h3 className="mb-2 font-medium text-green-600">
                  Available Places
                </h3>
                <ul className="list-inside list-disc">
                  {place.availablePlaces.map((item, i) => (
                    <li
                      key={i}
                      className="text-gray-700">
                      {item}
                    </li>
                  ))}
                </ul>
              </div>

              <div>
                <h3 className="mb-2 font-medium text-red-600">
                  Not Available Places
                </h3>
                <ul className="list-inside list-disc">
                  {place.notAvailablePlaces.map((item, i) => (
                    <li
                      key={i}
                      className="text-gray-700">
                      {item}
                    </li>
                  ))}
                </ul>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Favourites;
