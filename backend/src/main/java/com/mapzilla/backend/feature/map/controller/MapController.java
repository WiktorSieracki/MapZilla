package com.mapzilla.backend.feature.map.controller;

import com.mapzilla.backend.feature.history.utils.MapPoint;
import com.mapzilla.backend.feature.map.dto.MapResponseDto;
import com.mapzilla.backend.feature.map.dto.OverpassApiRequest;
import com.mapzilla.backend.feature.map.dto.OverpassQueryRequest;
import com.mapzilla.backend.feature.map.enums.PlaceType;
import com.mapzilla.backend.feature.map.service.MapService;
import com.mapzilla.backend.feature.map.service.OverpassApiClient;
import com.mapzilla.backend.feature.map.service.QueryBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/map")
@RequiredArgsConstructor
public class MapController {

    private final OverpassApiClient overpassApiClient;
    private final MapService mapService;

//    public MapController(OverpassApiClient overpassApiClient) {
//        this.overpassApiClient = overpassApiClient;
//    }

    @PostMapping("/locate")
    public MapResponseDto getMapData(@AuthenticationPrincipal Jwt jwt, @RequestBody OverpassApiRequest request) {


        return mapService.getMap(request);

//        return overpassApiClient.getMapData(
//                request.getLat(),
//                request.getLon(),
//                request.getRadius(),
//                selectedPlaces,
//                jwt
//        );
    }

}
