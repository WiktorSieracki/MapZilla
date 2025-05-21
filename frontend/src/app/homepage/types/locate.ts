import { Node, Way } from '../interface/map-objects';

export interface LocateRequest {
  lat: number;
  lon: number;
  radius: number;
  types: string[];
}

export interface LocateResponse {
  score: number;
  lat: number;
  lon: number;
  availablePlaces: string[];
  notAvailablePlaces: string[];
  places: (Node | Way)[];
}
