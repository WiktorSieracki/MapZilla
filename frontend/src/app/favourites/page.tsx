'use client';

import { FavoriteCard } from '@/app/favourites/components/favorite-card';
import { LocationComparison } from '@/app/favourites/components/location-comparison';
import { UpdateLabelsModal } from '@/app/favourites/components/update-labels-modal';
import { useFetchFavouritePlaces } from '@/app/favourites/hooks/client/use-fetch-favourite-places';
import type { FavouritePlace } from '@/app/favourites/types';
import { useSession } from 'next-auth/react';
import { useState } from 'react';
import { Label, useFetchLabels } from './hooks/client/use-fetch-labels';

const Favourites = () => {
  const [selectedLocations, setSelectedLocations] = useState<FavouritePlace[]>(
    []
  );
  const { data: session } = useSession();

  const [isUpdateModalOpen, setIsUpdateModalOpen] = useState(false);
  const [selectedPlaceId, setSelectedPlaceId] = useState<string | null>(null);

  const { data: favouritePlaces, refetch: refetchPlaces } =
    useFetchFavouritePlaces(session?.tokens?.accessToken as string);

  const handleLocationSelect = (location: FavouritePlace) => {
    setSelectedLocations((prev) => {
      if (prev.includes(location)) {
        return prev.filter((loc) => loc !== location);
      }
      if (prev.length >= 2) {
        return [prev[1], location];
      }
      return [...prev, location];
    });
  };

  const { data: labels, refetch: refetchLabels } = useFetchLabels(
    session?.tokens?.accessToken as string
  );

  const handleUpdatePlace = (placeId: string) => {
    setSelectedPlaceId(placeId);
    setIsUpdateModalOpen(true);
  };

  const handleUpdateLabels = async (selectedLabels: Label[]) => {
    if (!selectedPlaceId) return;

    // TODO: Implement API call to update labels
    console.log('Updating labels for place:', selectedPlaceId, selectedLabels);

    refetchPlaces();
    refetchLabels();
    setIsUpdateModalOpen(false);
  };

  return (
    <div className="mx-auto max-w-4xl p-6">
      <h1 className="mb-6 text-2xl font-bold">Your Favorite Locations</h1>
      <p className="mb-4 text-gray-600">
        {selectedLocations.length === 0
          ? 'Select two locations to compare them'
          : selectedLocations.length === 1
            ? 'Select one more location to compare'
            : 'Comparing two locations'}
      </p>
      {selectedLocations.length === 2 && (
        <LocationComparison
          location1={selectedLocations[0]}
          location2={selectedLocations[1]}
        />
      )}

      <div className="grid gap-4">
        {favouritePlaces?.data.map((place) => (
          <>
            <FavoriteCard
              key={place.id}
              place={place}
              isSelected={selectedLocations.includes(place)}
              onSelect={() => handleLocationSelect(place)}
              onUpdate={() => handleUpdatePlace(place.id)}
            />
            <UpdateLabelsModal
              isOpen={isUpdateModalOpen}
              onClose={() => setIsUpdateModalOpen(false)}
              onSave={handleUpdateLabels}
              availableLabels={labels?.data || []}
              currentLabels={place.labels}
            />
          </>
        ))}
      </div>
    </div>
  );
};

export default Favourites;
