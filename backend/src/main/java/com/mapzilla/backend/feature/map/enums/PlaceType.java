package com.mapzilla.backend.feature.map.enums;

public enum PlaceType {
    SHOP(TagKey.SHOP),
    RESTAURANT(TagKey.AMENITY, "restaurant"),
    HOSPITAL(TagKey.AMENITY, "hospital"),
    PLACE_OF_WORSHIP(TagKey.AMENITY, "place_of_worship"),
    CAFE(TagKey.AMENITY, "cafe"),
    PHARMACY(TagKey.AMENITY, "pharmacy"),
    BANK(TagKey.AMENITY, "bank"),
    CINEMA(TagKey.AMENITY, "cinema"),
    SCHOOL(TagKey.AMENITY, "school"),
    UNIVERSITY(TagKey.AMENITY, "university"),
    POLICE(TagKey.AMENITY, "police"),
    POST_OFFICE(TagKey.AMENITY, "post_office"),
    TOWNHALL(TagKey.AMENITY, "townhall"),
    PARK(TagKey.LEISURE, "park");

    private final String key;
    private final String value;

    PlaceType(TagKey tag) {
        this.key = tag.toString();
        this.value = null;
    }

    PlaceType(TagKey key, String value) {
        this.key = key.toString();
        this.value = value;
    }

    public String toOverpassTag() {
        return value == null
                ? String.format("[\"%s\"]", key)
                : String.format("[\"%s\"=\"%s\"]", key, value);
    }
}
