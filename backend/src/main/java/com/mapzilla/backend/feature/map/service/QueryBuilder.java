package com.mapzilla.backend.feature.map.service;

import com.mapzilla.backend.feature.map.enums.PlaceType;

import java.util.Set;
import java.util.stream.Collectors;

public class QueryBuilder {

    public static String buildQuery(double lat, double lon, Set<PlaceType> types, int radius) {
        return types.stream()
                .map(type -> buildPlaceQuery(type, lat, lon, radius))
                .collect(Collectors.joining("\n"));
    }

    private static String buildPlaceQuery(PlaceType type, double lat, double lon, int radius) {
        String tag = type.toOverpassTag();
        return String.format("""
            node%s(around:%d, %f, %f);
            way%s(around:%d, %f, %f);
            relation%s(around:%d, %f, %f);
            """, tag, radius, lat, lon, tag, radius, lat, lon, tag, radius, lat, lon);
    }
}
