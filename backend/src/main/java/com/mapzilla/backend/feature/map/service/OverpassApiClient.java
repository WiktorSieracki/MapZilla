package com.mapzilla.backend.feature.map.service;

import com.mapzilla.backend.feature.history.service.HistoryService;
import com.mapzilla.backend.feature.history.utils.Location;
import com.mapzilla.backend.feature.history.utils.MapPoint;
import com.mapzilla.backend.feature.map.dto.OverpassResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class OverpassApiClient {

    private final WebClient webClient;
//    private final HistoryService historyService;
    private final OverpassMapper mapper;

    private static final int MAX_IN_MEMORY_SIZE = 5 * 1024 * 1024; // np. 5 MB

    @Value("${app.api.base-url}")
    private String overpassApiBaseUrl;

    public OverpassApiClient(WebClient.Builder webClientBuilder, HistoryService historyService, OverpassMapper mapper) {
        this.mapper = mapper;
        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(MAX_IN_MEMORY_SIZE))
                .build();

        this.webClient = webClientBuilder
                .baseUrl("https://overpass-api.de")
                .defaultHeader("Accept", "application/json")
                .exchangeStrategies(strategies)
                .build();
//        this.historyService = historyService;
    }

    public Mono<List<MapPoint>> getMapData(double lat, double lon, int radius, String selectedPlaces) {
        String overpassQuery = String.format("""
            [out:json];
            (
                %s
            );
            out geom;
            """, selectedPlaces);

        log.info("Sending Overpass query:\n{}", overpassQuery);

        return webClient.post()
            .uri("/api/interpreter")
            .bodyValue("data=" + overpassQuery)
            .retrieve()
            .bodyToMono(OverpassResponse.class)
                .map(mapper::toMapPoints);
    }

}
