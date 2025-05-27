export interface FavouritePlace {
  id: string;
  score: number;
  lat: number;
  lon: number;
  availablePlaces: string[];
  notAvailablePlaces: string[];
  labels: string[];
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
