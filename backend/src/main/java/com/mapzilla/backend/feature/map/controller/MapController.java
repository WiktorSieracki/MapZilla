package com.mapzilla.backend.feature.map.controller;

import com.mapzilla.backend.feature.map.dto.OverpassApiRequest;
import com.mapzilla.backend.feature.map.dto.OverpassQueryRequest;
import com.mapzilla.backend.feature.map.enums.PlaceType;
import com.mapzilla.backend.feature.map.service.OverpassApiClient;
import com.mapzilla.backend.feature.map.service.QueryBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/map")
public class MapController {

    private final OverpassApiClient overpassApiClient;

    public MapController(OverpassApiClient overpassApiClient) {
        this.overpassApiClient = overpassApiClient;
    }

    @PostMapping("/locate")
    public Mono<String> getMapData(@RequestBody OverpassApiRequest request) {
        String selectedPlaces = QueryBuilder.buildQuery(
            request.getLat(),
            request.getLon(),
            Set.copyOf(request.getTypes()),
            request.getRadius()

    );

        return overpassApiClient.getMapData(
                request.getLat(),
                request.getLon(),
                request.getRadius(),
                selectedPlaces
        );
    }

}
