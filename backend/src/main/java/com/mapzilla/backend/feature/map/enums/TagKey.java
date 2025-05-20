package com.mapzilla.backend.feature.map.enums;

import lombok.Data;
import lombok.Getter;

@Getter
public enum TagKey {
    AMENITY("amenity"),
    LEISURE("leisure"),
    SHOP ("shop"),
    TOURISM("tourism"),
    HIGHWAY("highway"),
    RAILWAY("railway"),
    BUILDING("building"),;

    private final String name;

    TagKey(String s) {
        name = s;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
