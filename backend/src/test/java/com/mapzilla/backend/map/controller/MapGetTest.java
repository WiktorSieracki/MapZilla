package com.mapzilla.backend.map.controller;

//import com.mapzilla.backend.feature.map.controller.MapController;
//import com.mapzilla.backend.feature.map.service.OverpassApiClient;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import reactor.core.publisher.Mono;
//
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class MapGetTest {
//
//    private OverpassApiClient apiClient;
//
//    @Mock
//    private WebClient.Builder webClientBuilder;
//
//    @Mock
//    private WebClient mockWebClient;
//
//    @Mock
//    private WebClient.RequestBodyUriSpec requestSpec;
//
//    @Mock
//    private WebClient.RequestBodySpec bodySpec;
//
//    @Mock
//    private WebClient.ResponseSpec responseSpec;
//
//    @BeforeEach
//    void setUp() {
//        // Setup WebClient mocks
//        when(webClientBuilder.baseUrl(anyString())).thenReturn(webClientBuilder);
//        when(webClientBuilder.build()).thenReturn(mockWebClient);
//        apiClient = new OverpassApiClient(webClientBuilder);
//    }
//
//    @Test
//    void shouldCallOverpassWithCorrectQuery() {
//        // given
//        String square = "[out:json][timeout:90];";
//        String centerX = "54.3948";
//        String centerY = "18.5743";
//        String selectedPlaces = """
//                node(around.center:1200)["amenity"="restaurant"];
//                way(around.center:1200)["amenity"="restaurant"];
//                relation(around.center:1200)["amenity"="restaurant"];
//                """;
//
//        String expectedQuery = String.format("""
//            %s
//
//            node(around:1200, %s, %s)->.center;
//            (
//                %s
//            );
//            out geom;
//            """, square, centerX, centerY, selectedPlaces);
//
//        // mockowanie flow WebClienta
//        when(mockWebClient.post()).thenReturn(requestSpec);
//        when(requestSpec.uri("/api/interpreter")).thenReturn(bodySpec);
//        when(bodySpec.bodyValue("data=" + expectedQuery)).thenReturn(responseSpec);
//        when(responseSpec.retrieve()).thenReturn(responseSpec);
//        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just("mock response"));
//
//        // when
//        Mono<String> result = apiClient.getMapData(square, centerX, centerY, selectedPlaces);
//
//        // then
//        StepVerifier.create(result)
//                .expectNext("mock response")
//                .verifyComplete();
//
//        // verify if the expected query was used
//        verify(bodySpec).bodyValue("data=" + expectedQuery);
//    }
//
//}
