export interface CenterContextProps {
  center: { x: number; y: number };
  setCenter: (center: { x: number; y: number }) => void;
  data: any;
  setData: (data: any) => void;
  locationCenter?: { x: number; y: number };
  setLocationCenter?: (locationCenter: { x: number; y: number }) => void;
}
