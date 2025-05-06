import { Node, Way } from '@/app/homepage/interface/map-objects';

export interface OverpassApiResponse {
  version: number;
  generator: string;
  elements: Node[] | Way[];
  osm3s: {
    copyright: string;
    timestamp_osm_base: string;
  };
}
