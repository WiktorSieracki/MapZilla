package com.mapzilla.backend.feature.map.controller;

import com.mapzilla.backend.feature.map.service.OverpassApiClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

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
}
