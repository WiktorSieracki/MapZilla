package com.mapzilla.backend.feature.map.controller;

import com.mapzilla.backend.feature.map.service.OverpassApiClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class MapController {

    private final OverpassApiClient overpassApiClient;

    public MapController(OverpassApiClient overpassApiClient) {
        this.overpassApiClient = overpassApiClient;
    }

    @GetMapping("/restaurants")
    public Mono<String> getRestaurants(@RequestParam(defaultValue = "Warszawa") String city) {
        String overpassQuery = """
                [out:json];
                node[amenity=restaurant][name~"Pizza"](around:1000,52.2298,21.0122);
                out body;""";
        return overpassApiClient.getMapData(overpassQuery);
    }

    @GetMapping("/places/nearby")
    public Mono<String> getNearbyPlaces(
            @RequestParam double lat,
            @RequestParam double lon
    ) {
        String overpassQuery = String.format("""
            [out:json];
            node[amenity=restaurant](around:1500,%.5f,%.5f);
            out body;
            """, lat, lon);

        return overpassApiClient.getMapData(overpassQuery);
    }

    @GetMapping("/places/nearby/personalized")
    public Mono<String> getNearbyPlacesPersonalized(
            @RequestParam double lat,
            @RequestParam double lon,
            @RequestParam List<String> amenities
    ) {
        StringBuilder amenitiesFilter = new StringBuilder();

        for (String amenity : amenities) {
            if (amenitiesFilter.length() > 0) {
                amenitiesFilter.append(","); // oddzielamy różne warunki
            }
            amenitiesFilter.append("node[amenity=").append(amenity).append("]");
        }

        // Tworzymy pełne zapytanie
        String overpassQuery = String.format("""
        [out:json];
        %s(around:1500,%.5f,%.5f);
        out body;
    """, amenitiesFilter.toString(), lat, lon);

        return overpassApiClient.getMapData(overpassQuery);
    }

}
