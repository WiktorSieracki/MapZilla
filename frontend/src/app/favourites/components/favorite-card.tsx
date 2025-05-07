import { FavoritePlace } from '@/app/favourites/types';

interface FavoriteCardProps {
  place: FavoritePlace;
  index: number;
  isSelected: boolean;
  onSelect: () => void;
}

export const FavoriteCard = ({
  place,
  isSelected,
  onSelect,
}: FavoriteCardProps) => {
  return (
    <div
      className={`rounded-lg p-4 shadow-md transition-colors ${
        isSelected ? 'border-2 border-blue-500 bg-blue-50' : 'bg-white'
      }`}
      onClick={onSelect}
      onKeyDown={(e) => {
        if (e.key === 'Enter' || e.key === ' ') {
          onSelect();
        }
      }}
      role="button"
      tabIndex={0}>
      <div className="mb-4 flex items-start justify-between">
        <div>
          <h2 className="mb-2 text-xl font-semibold">{place.name}</h2>
          <p className="text-gray-600">
            Coordinates: {place.lat.toFixed(4)}, {place.lon.toFixed(4)}
          </p>
        </div>
        <div className="rounded bg-blue-100 px-3 py-1 font-medium text-blue-800">
          Score: {Math.round(place.score * 100)}%
        </div>
      </div>

      <div className="grid gap-4 md:grid-cols-2">
        <div>
          <h3 className="mb-2 font-medium text-green-600">Available Places</h3>
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
  );
};
