import { OverpassApiResponse } from '@/app/homepage/interface/api-response';

export interface CenterContextProps {
  center: { x: number; y: number };
  setCenter: (center: { x: number; y: number }) => void;
  data: OverpassApiResponse | null;
  setData: (data: OverpassApiResponse) => void;
  searchKey: number;
  setSearchKey: (key: number) => void;
  locationCenter?: { x: number; y: number };
  setLocationCenter?: (locationCenter: { x: number; y: number }) => void;
}
