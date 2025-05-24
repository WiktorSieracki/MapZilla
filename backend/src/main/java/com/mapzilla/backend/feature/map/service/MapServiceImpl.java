package com.mapzilla.backend.feature.map.service;

import com.mapzilla.backend.feature.history.service.HistoryService;
import com.mapzilla.backend.feature.history.utils.Location;
import com.mapzilla.backend.feature.history.utils.MapPoint;
import com.mapzilla.backend.feature.map.dto.MapResponse;
import com.mapzilla.backend.feature.map.dto.MapResponseDto;
import com.mapzilla.backend.feature.map.dto.OverpassApiRequest;
import com.mapzilla.backend.feature.map.enums.PlaceType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MapServiceImpl implements MapService {
    private final OverpassApiClient overpassApiClient;
    private final HistoryService historyService;

    @Override
    public MapResponseDto getMap(Jwt jwt, OverpassApiRequest request) {
        String selectedPlaces = QueryBuilder.buildQuery(
                request.getLat(),
                request.getLon(),
                Set.copyOf(request.getTypes()),
                request.getRadius()
        );

        List<MapPoint> mapPoints = overpassApiClient.getMapData(
                request.getLat(),
                request.getLon(),
                request.getRadius(),
                selectedPlaces
        ).block();

        Set<PlaceType> selectedTypes = Set.copyOf(request.getTypes());

        MapResponse mapResponse = new MapResponse();

        mapResponse.setLat(BigDecimal.valueOf(request.getLat()));
        mapResponse.setLon(BigDecimal.valueOf(request.getLon()));

        Set<PlaceType> availablePlaces = selectedTypes.stream()
                .filter(place -> mapPoints.stream().anyMatch(point -> {
                    Map<String, String> tags = point.getTags();
                    String tagValue = tags.get(place.getKey());

                    return place.getQueryName() != null
                            ? place.getQueryName().equals(tagValue)
                            : tagValue != null;
                }))
                .collect(Collectors.toSet());

        Set<PlaceType> missingPlaces = selectedTypes.stream()
                .filter(place -> !availablePlaces.contains(place))
                .collect(Collectors.toSet());

        mapResponse.setAvailablePlaces(availablePlaces);
        mapResponse.setNotAvailablePlaces(missingPlaces);
        mapResponse.setPlaces(mapPoints);
        mapResponse.setScore(calculateScore(availablePlaces, missingPlaces, selectedTypes));

        Location location = Location.from(mapResponse);
        historyService.addToHistory(jwt, location);

        return MapResponseDto.from(mapResponse);
    }

    private BigDecimal calculateScore(Set<PlaceType> availablePlaces, Set<PlaceType> notAvailablePlaces, Set<PlaceType> allPlaces) {

        return BigDecimal.valueOf((double) availablePlaces.size() / (double) allPlaces.size()).setScale(2, RoundingMode.HALF_UP);
    }
}
