'use client';

import { useCenterContext } from '@/app/homepage/context/center-context';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { useState } from 'react';

export const Search = () => {
  const [searchQuery, setSearchQuery] = useState('');
  const { setLocationCenter } = useCenterContext();

  const handleSearch = async () => {
    try {
      const response = await fetch(
        `https://nominatim.openstreetmap.org/search?format=json&q=${encodeURIComponent(
          searchQuery
        )}`
      );
      const data = await response.json();
      console.log('Search results:', data);

      if (data && data.length > 0 && setLocationCenter) {
        console.log(data[0].lat, data[0].lon);
        setLocationCenter({
          x: parseFloat(data[0].lat),
          y: parseFloat(data[0].lon),
        });
      }
    } catch (error) {
      console.error('Error searching location:', error);
    }
  };

  return (
    <div className="mb-4 flex gap-2">
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
      />
      <Button onClick={handleSearch}>Search</Button>
    </div>
  );
};
