export interface Tags {
  'name': string;
  'amenity'?: string;
  'leisure'?: string;
  'addr:city'?: string;
  [key: string]: string | undefined;
}
export interface Node {
  id: number;
  lat: number;
  lon: number;
  tags?: Tags;
  type: 'node';
}

export interface Way {
  id: number;
  geometry: { lat: number; lon: number }[];
  tags?: Tags;
  type: 'way';
}
