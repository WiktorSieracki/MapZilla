import { FavouritePlace } from '@/app/favourites/types';

interface FavoriteCardProps {
  place: FavouritePlace;
  isSelected: boolean;
  onSelect: () => void;
  onUpdate: () => void;
}

export const FavoriteCard = ({
  place,
  isSelected,
  onSelect,
  onUpdate,
}: FavoriteCardProps) => {
  return (
    <div
      className={`rounded-lg p-4 shadow-md transition-colors ${isSelected ? 'border-2 border-blue-500 bg-blue-50' : 'bg-white'}`}
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
          <div className="mb-2 flex flex-wrap gap-2">
            {place.labels.map((label) => (
              <span
                key={label.id}
                className="inline-flex items-center rounded-full bg-neutral-600 px-2.5 py-0.5 text-xl font-medium"
                style={{
                  backgroundColor: label.color.toLowerCase() + '20',
                  color: label.color.toLowerCase(),
                }}>
                {label.name}
              </span>
            ))}
          </div>
          <p className="text-gray-600">
            Coordinates: {place.lat.toFixed(4)}, {place.lon.toFixed(4)}
          </p>
        </div>
        <button
          onClick={onUpdate}
          className="ml-4 rounded bg-blue-500 px-3 py-1 text-sm text-white transition-colors hover:bg-blue-600">
          Update
        </button>
        <div className="rounded bg-blue-100 px-3 py-1 font-medium text-blue-800">
          Score: {Math.round(place.score * 100)}%
        </div>
      </div>

      <div className="grid gap-4 md:grid-cols-2">
        <div>
          <h3 className="mb-2 font-medium text-green-600">Available Places</h3>
          <ul className="list-inside list-disc">
            {place.availablePlaces.map((item) => (
              <li
                key={item}
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
            {place.notAvailablePlaces.map((item) => (
              <li
                key={item}
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
