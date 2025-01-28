export type Prefixes = "amenity" | "leisure"

export type Amenities = "park"

export type Leisures = "place_of_worship" | "pharmacy" | "school"

export interface Place {
  id: number
  prefix: Prefixes
  queryName: Amenities | Leisures
  readableName: string
  color: string
}
