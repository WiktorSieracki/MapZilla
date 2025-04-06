package com.mapzilla.backend.feature.map.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class OverpassApiClient {

    private final WebClient webClient;

    public OverpassApiClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://overpass-api.de/api").build();
    }

    public Mono<String> getMapData(String query) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/interpreter")
                        .queryParam("data", query)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }
}
