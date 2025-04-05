export interface Node {
  id: number;
  lat: number;
  lon: number;
  tags?: {
    'addr:city': string;
    'addr:housenumber'?: string;
    'amenity'?: string;
    'leisure'?: string;
    'name': string;
  };
  type: 'node';
}

export interface Way {
  id: number;
  geometry: { lat: number; lon: number }[];
  tags?: { name: string; amenity?: string; leisure?: string };
  type: 'way';
}
