'use client';

import { useCenterContext } from '@/app/homepage/context/center-context';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { useQuery } from '@tanstack/react-query';
import { useState } from 'react';

const searchLocation = async (query: string) => {
  if (!query) return null;
  const response = await fetch(
    `https://nominatim.openstreetmap.org/search?format=json&q=${encodeURIComponent(query)}`
  );
  return response.json();
};

export const Search = () => {
  const [searchQuery, setSearchQuery] = useState('');
  const { setCenter, setSearchKey } = useCenterContext();

  const { data, isLoading, error, refetch } = useQuery({
    queryKey: ['location', searchQuery],
    queryFn: () => searchLocation(searchQuery),
    enabled: false,
    retry: false,
  });

  const handleSearch = async () => {
    const result = await refetch();
    if (result.data && result.data.length > 0) {
      setCenter({
        x: parseFloat(result.data[0].lat),
        y: parseFloat(result.data[0].lon),
      });
      setSearchKey(Date.now()); // Use timestamp as key to force reload
    }
  };

  return (
    <div className="mb-4 flex gap-2">
      <div className="relative flex-1">
        <Input
          type="text"
          placeholder="Search for a place..."
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
          onKeyDown={(e) => {
            if (e.key === 'Enter') {
              handleSearch();
            }
          }}
          className={isLoading ? 'pr-8' : ''}
        />
        {isLoading && (
          <div className="absolute right-3 top-1/2 -translate-y-1/2">
            <div className="h-4 w-4 animate-spin rounded-full border-2 border-gray-300 border-t-black" />
          </div>
        )}
      </div>
      <Button
        onClick={handleSearch}
        disabled={isLoading}>
        {isLoading ? 'Searching...' : 'Search'}
      </Button>
    </div>
  );
};
