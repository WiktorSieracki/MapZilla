export type Prefixes = "amenity" | "leisure";

// export type Leisures = "park";

// export type Amenities = "place_of_worship" | "pharmacy" | "school";

export interface Place {
  id: number;
  prefix: Prefixes;
  queryName: string;
  readableName: string;
  color: string;
}

export const places: Place[] = [
  {
    id: 1,
    prefix: "amenity",
    queryName: "place_of_worship",
    readableName: "Place of Worship",
    color: "red",
  },
  {
    id: 2,
    prefix: "leisure",
    queryName: "park",
    readableName: "Park",
    color: "green",
  },
  {
    id: 3,
    prefix: "amenity",
    queryName: "pharmacy",
    readableName: "Pharmacy",
    color: "blue",
  },
  {
    id: 4,
    prefix: "amenity",
    queryName: "restaurant",
    readableName: "Restaurant",
    color: "purple",
  },
  {
    id: 5,
    prefix: "amenity",
    queryName: "school",
    readableName: "School",
    color: "gray",
  },
  {
    id: 6,
    prefix: "amenity",
    queryName: "bank",
    readableName: "Bank",
    color: "yellow",
  },
];
