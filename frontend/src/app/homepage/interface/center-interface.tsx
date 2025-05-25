import { Response } from '@/app/services/backend-api/types/response';
import { LocateResponse } from '@/app/homepage/types/locate';

export interface CenterContextProps {
  center: { x: number; y: number };
  setCenter: (center: { x: number; y: number }) => void;
  data: Response<LocateResponse> | null;
  setData: (data: Response<LocateResponse>) => void;
  searchKey: number;
  setSearchKey: (key: number) => void;
  locationCenter?: { x: number; y: number };
  setLocationCenter?: (locationCenter: { x: number; y: number }) => void;
}
