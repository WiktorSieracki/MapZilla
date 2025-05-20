package com.mapzilla.backend.feature.map.controller;

import com.mapzilla.backend.feature.history.utils.MapPoint;
import com.mapzilla.backend.feature.map.dto.MapResponseDto;
import com.mapzilla.backend.feature.map.dto.OverpassApiRequest;
import com.mapzilla.backend.feature.map.dto.OverpassQueryRequest;
import com.mapzilla.backend.feature.map.enums.PlaceType;
import com.mapzilla.backend.feature.map.service.MapService;
import com.mapzilla.backend.feature.map.service.OverpassApiClient;
import com.mapzilla.backend.feature.map.service.QueryBuilder;
import com.mapzilla.backend.feature.util.dto.ApiResponse;
import com.mapzilla.backend.feature.util.enums.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/map")
@RequiredArgsConstructor
public class MapController {

    private final MapService mapService;

    @PostMapping("/locate")
    public ApiResponse<MapResponseDto> getMapData(@AuthenticationPrincipal Jwt jwt, @RequestBody OverpassApiRequest request) {

        return new ApiResponse<>(
                SuccessCode.RESPONSE_SUCCESSFUL,
                "Successfully fetched information",
                mapService.getMap(jwt, request)
        );
    }

}
