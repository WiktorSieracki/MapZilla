package com.mapzilla.backend.feature.map.service;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class OverpassApiClient {

    private final WebClient webClient;

    private static final int MAX_IN_MEMORY_SIZE = 5 * 1024 * 1024; // np. 5 MB

    @Value("${app.api.base-url}")
    private String apiUrl;

    public OverpassApiClient(WebClient.Builder webClientBuilder) {
        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(MAX_IN_MEMORY_SIZE))
                .build();

        this.webClient = webClientBuilder
                .baseUrl("https://overpass-api.de")
                .defaultHeader("Accept", "application/json")
                .exchangeStrategies(strategies)
                .build();
    }

    public Mono<String> getMapData(double lat, double lon, int radius, String selectedPlaces) {
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
            .bodyToMono(String.class);
    }

}
