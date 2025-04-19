package com.mapzilla.backend.feature.map.controller;

import com.mapzilla.backend.feature.map.dto.OverpassQueryRequest;
import com.mapzilla.backend.feature.map.service.OverpassApiClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
public class MapController {

    private final OverpassApiClient overpassApiClient;

    public MapController(OverpassApiClient overpassApiClient) {
        this.overpassApiClient = overpassApiClient;
    }

//    @GetMapping("/restaurants")
//    public Mono<String> getRestaurants(@RequestParam(defaultValue = "Warszawa") String city) {
//        String overpassQuery = """
//                [out:json];
//                node[amenity=restaurant][name~"Pizza"](around:1000,52.2298,21.0122);
//                out body;""";
//        return overpassApiClient.getMapData(overpassQuery);
//    }

//    @GetMapping("/places/nearby")
//    public Mono<String> getNearbyPlaces(
//            @RequestParam double lat,
//            @RequestParam double lon
//    ) {
//        String overpassQuery = String.format("""
//            [out:json];
//            node[amenity=restaurant](around:1500,%.5f,%.5f);
//            out body;
//            """, lat, lon);
//
//        return overpassApiClient.getMapData(overpassQuery);
//    }
    @GetMapping("/places/nearby")
    public Mono<String> getNearbyPlaces(
            @RequestParam String square,
            @RequestParam String centerX,
            @RequestParam String centerY,
            @RequestParam String selectedPlaces) {

        return overpassApiClient.getMapData(square, centerX, centerY, selectedPlaces);
    }

    @PostMapping("/places/nearby")
    public Mono<String> getNearbyPlaces(@RequestBody OverpassQueryRequest request) {
        return overpassApiClient.getMapData(
                request.getSquare(),
                request.getCenterX(),
                request.getCenterY(),
                request.getSelectedPlaces()
        );
    }


//    @GetMapping("/places/nearby/personalized")
//    public Mono<String> getNearbyPlacesPersonalized(
//            @RequestParam double lat,
//            @RequestParam double lon,
//            @RequestParam List<String> amenities
//    ) {
//        StringBuilder amenitiesFilter = new StringBuilder();
//
//        for (String amenity : amenities) {
//            if (!amenitiesFilter.isEmpty()) {
//                amenitiesFilter.append(",");
//            }
//            amenitiesFilter.append("node[amenity=").append(amenity).append("]");
//        }
//
//        String overpassQuery = "[out:json];node[amenity=restaurant],node[amenity=cafe](around:1500,50.00000,20.00000);out body;";
//        String encodedQuery = UriUtils.encode(overpassQuery, StandardCharsets.UTF_8);
//
////        String overpassQuery = String.format("""
////        [out:json];
////        %s(around:1500,%.5f,%.5f);
////        out body;
////    """, amenitiesFilter.toString(), lat, lon);
//
//        return overpassApiClient.getMapData(encodedQuery);
//    }

}
