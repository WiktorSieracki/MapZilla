'use client';

import { FavoriteCard } from '@/app/favourites/components/favorite-card';
import { LocationComparison } from '@/app/favourites/components/location-comparison';
import type { FavoritePlace } from '@/app/favourites/types';
import { useState } from 'react';

const Favourites = () => {
  const [selectedLocations, setSelectedLocations] = useState<FavoritePlace[]>(
    []
  );

  // For now using mock data, later we'll fetch from API
  const favorites: FavoritePlace[] = [
    {
      name: 'Warsaw, Poland',
      score: 2 / 3,
      lat: 52.2298,
      lon: 21.0122,
      availablePlaces: ['Place of Worship', 'Park', 'Hospital', 'Restaurant'],
      notAvailablePlaces: ['Cinema', 'Bank'],
    },
    {
      name: 'Rome, Italy',
      score: 2 / 3,
      lat: 41.8919,
      lon: 12.5113,
      availablePlaces: ['Restaurant', 'Park', 'Place of Worship', 'Bank'],
      notAvailablePlaces: ['Hospital', 'Cinema'],
    },
    {
      name: 'Paris, France',
      score: 2 / 3,
      lat: 48.8566,
      lon: 2.3522,
      availablePlaces: ['Cinema', 'Restaurant', 'Hospital', 'Pharmacy'],
      notAvailablePlaces: ['Park', 'School'],
    },
    {
      name: 'London, UK',
      score: 2 / 3,
      lat: 51.5074,
      lon: -0.1278,
      availablePlaces: ['School', 'Hospital', 'Park', 'Bank'],
      notAvailablePlaces: ['Cinema', 'Place of Worship'],
    },
  ];

  const handleLocationSelect = (location: FavoritePlace) => {
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
        {favorites.map((place, index) => (
          <FavoriteCard
            key={index}
            place={place}
            index={index}
            isSelected={selectedLocations.includes(place)}
            onSelect={() => handleLocationSelect(place)}
          />
        ))}
      </div>
    </div>
  );
};

export default Favourites;
