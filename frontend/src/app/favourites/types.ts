import { Label } from '@/app/favourites/hooks/client/use-fetch-labels';

export interface FavouritePlace {
  id: string;
  score: number;
  lat: number;
  lon: number;
  availablePlaces: string[];
  notAvailablePlaces: string[];
  labels: Label[];
}

export interface ComparisonResult {
  commonAvailable: string[];
  commonNotAvailable: string[];
  uniqueAvailable1: string[];
  uniqueNotAvailable1: string[];
  uniqueAvailable2: string[];
  uniqueNotAvailable2: string[];
  distanceKm: number;
  scoreDifference: number;
}
