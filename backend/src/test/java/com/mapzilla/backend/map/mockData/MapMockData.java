package com.mapzilla.backend.map.mockData;

import com.mapzilla.backend.feature.map.dto.MapResponseDto;
import com.mapzilla.backend.feature.map.dto.OverpassApiRequest;
import com.mapzilla.backend.feature.map.enums.PlaceType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class MapMockData {

    public static OverpassApiRequest createValidRequest() {
        OverpassApiRequest request = new OverpassApiRequest();
        request.setLat(52.23);
        request.setLon(21.01);
        request.setRadius(1000);
        request.setTypes(List.of(PlaceType.PARK, PlaceType.RESTAURANT));
        return request;
    }


    public static String createMissingTypesJson() {
        return """
                {
                    "lat": 52.23,
                    "lon": 21.01,
                    "radius": 1000
                }
                """;
    }

    public static String createInvalidLatitudeJson() {
        return """
                {
                    "lat": -10.5,
                    "lon": 21.01,
                    "radius": 1000,
                    "types": ["PARK"]
                }
                """;
    }

    public static String createInvalidLongitudeJson() {
        return """
                {
                    "lat": 52.23,
                    "lon": -5.0,
                    "radius": 1000,
                    "types": ["RESTAURANT"]
                }
                """;
    }
}
