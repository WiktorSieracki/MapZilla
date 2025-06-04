package com.mapzilla.backend.favouritePlace.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapzilla.backend.exceptions.ResourceNotFoundException;
import com.mapzilla.backend.feature.favouritePlace.controller.FavouritePlaceController;
import com.mapzilla.backend.feature.favouritePlace.dto.FavouritePlaceCreateDto;
import com.mapzilla.backend.feature.favouritePlace.dto.FavouritePlaceResponseDto;
import com.mapzilla.backend.feature.favouritePlace.service.FavouritePlaceService;
import com.mapzilla.backend.feature.user.service.UserSynchronizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = FavouritePlaceController.class)
@AutoConfigureMockMvc(addFilters = false)
class FavouritePlaceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FavouritePlaceService favouritePlaceService;

    @MockitoBean
    private UserSynchronizer userSynchronizer;

    @Autowired
    private ObjectMapper objectMapper;

    private FavouritePlaceCreateDto validCreateDto;

    @BeforeEach
    void setUp() {
        Jwt jwt = Jwt.withTokenValue("fake-token")
                .header("alg", "none")
                .claim("sub", "user123")
                .build();
        Authentication auth = Mockito.mock(Authentication.class);
        when(auth.getPrincipal()).thenReturn(jwt);
        SecurityContextHolder.getContext().setAuthentication(auth);

        validCreateDto = FavouritePlaceMockData.createValidFavouritePlaceCreateDto();

    }



    //===================AddFavouritePlace==================

    @Test
    void createPlace_ReturnCreated() throws Exception {
        FavouritePlaceResponseDto responseDto = new FavouritePlaceResponseDto();
        responseDto.setId(UUID.randomUUID());
        responseDto.setScore(validCreateDto.getScore());
        responseDto.setLat(validCreateDto.getLat());
        responseDto.setLon(validCreateDto.getLon());
        responseDto.setAvailablePlaces(validCreateDto.getAvailablePlaces());
        responseDto.setNotAvailablePlaces(validCreateDto.getNotAvailablePlaces());

        when(favouritePlaceService.addFavouritePlace(any(Jwt.class), any(FavouritePlaceCreateDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/fav-place")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validCreateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Successfully added place to list of favourites"));
    }


    @Test
    void createLocation_ReturnCreated_CheckResponse() throws Exception {
        FavouritePlaceResponseDto responseDto = new FavouritePlaceResponseDto();
        responseDto.setId(UUID.randomUUID());
        responseDto.setScore(validCreateDto.getScore());
        responseDto.setLat(validCreateDto.getLat());
        responseDto.setLon(validCreateDto.getLon());
        responseDto.setAvailablePlaces(validCreateDto.getAvailablePlaces());
        responseDto.setNotAvailablePlaces(validCreateDto.getNotAvailablePlaces());

        when(favouritePlaceService.addFavouritePlace(any(Jwt.class), any(FavouritePlaceCreateDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/fav-place")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validCreateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Successfully added place to list of favourites"))
                .andExpect(jsonPath("$.data.score").value(10.0))
                .andExpect(jsonPath("$.data.lat").value(50.0))
                .andExpect(jsonPath("$.data.lon").value(20.0));
    }

    @Test
    void addFavouritePlace_ReturnBadRequest_Score() throws Exception {
        FavouritePlaceCreateDto requestBadScore = FavouritePlaceMockData.createInvalidScoreDto();

        mockMvc.perform(post("/fav-place")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBadScore)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void addFavouritePlace_ReturnBadRequest_Lat() throws Exception {
        FavouritePlaceCreateDto requestBadLat = FavouritePlaceMockData.createInvalidLatDto();

        mockMvc.perform(post("/fav-place")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBadLat)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void addFavouritePlace_ReturnBadRequest_Lon() throws Exception {
        FavouritePlaceCreateDto requestBadLon = FavouritePlaceMockData.createInvalidLonDto();

        mockMvc.perform(post("/fav-place")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBadLon)))
                .andExpect(status().isBadRequest());
    }
    //===================GetFavouritePlacesForUser==================

    @Test
    void GetAllFavouritePlaces_ReturnSuccess_WithResults() throws Exception {
        Jwt jwt = Jwt.withTokenValue("fake-token")
                .header("alg", "none")
                .claim("sub", "user123")
                .build();

        List<FavouritePlaceResponseDto> responseList = List.of(
                FavouritePlaceMockData.createMockResponseDto(),
                FavouritePlaceMockData.createMockResponseDto()
        );

        when(favouritePlaceService.getAllFavouritePlaces(jwt)).thenReturn(responseList);

        Authentication auth = Mockito.mock(Authentication.class);
        when(auth.getPrincipal()).thenReturn(jwt);
        SecurityContextHolder.getContext().setAuthentication(auth);

        mockMvc.perform(get("/fav-place")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Successfully fetched favourite places of a user"))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(2));
    }

    @Test
    void GetAllFavouritePlaces_ReturnSuccess_Empty() throws Exception {
        Jwt jwt = Jwt.withTokenValue("fake-token")
                .header("alg", "none")
                .claim("sub", "user123")
                .build();

        when(favouritePlaceService.getAllFavouritePlaces(jwt)).thenReturn(List.of());

        Authentication auth = Mockito.mock(Authentication.class);
        when(auth.getPrincipal()).thenReturn(jwt);
        SecurityContextHolder.getContext().setAuthentication(auth);

        mockMvc.perform(get("/fav-place")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Successfully fetched favourite places of a user"))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(0));
    }

    //========================DeleteFavouritePlace=================

    @Test
    public void DeleteFavouritePlaceById_ReturnSuccess() throws Exception {
        UUID placeId = UUID.randomUUID();
        doNothing().when(favouritePlaceService).deleteFavouriteLocationById(any(Jwt.class), eq(placeId));

        mockMvc.perform(delete("/fav-place/delete/{id}", placeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Successfully deleted favourite place from list"));
    }

    @Test
    public void DeleteFavouritePlaceById_ReturnNotFound() throws Exception {
        UUID placeId = UUID.randomUUID();
        doThrow(new ResourceNotFoundException("Favourite place not found"))
                .when(favouritePlaceService).deleteFavouriteLocationById(any(Jwt.class), eq(placeId));

        mockMvc.perform(delete("/fav-place/delete/{id}", placeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Favourite place not found"));
    }


    //====================GetFavouritePlaceById==================================


    @Test
    public void GetFavouritePlaceById_ReturnSuccess() throws Exception {
        UUID placeId = UUID.randomUUID();

        FavouritePlaceResponseDto responseDto = new FavouritePlaceResponseDto();
        responseDto.setId(placeId);
        responseDto.setScore(BigDecimal.valueOf(10.0));
        responseDto.setLat(BigDecimal.valueOf(50.0));
        responseDto.setLon(BigDecimal.valueOf(20.0));

        when(favouritePlaceService.getFavouritePlaceById(any(Jwt.class), eq(placeId)))
                .thenReturn(responseDto);

        mockMvc.perform(get("/fav-place/{id}", placeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Successfully fetched favourite place with id " + placeId))
                .andExpect(jsonPath("$.data.id").value(placeId.toString()))
                .andExpect(jsonPath("$.data.score").value(10.0))
                .andExpect(jsonPath("$.data.lat").value(50.0))
                .andExpect(jsonPath("$.data.lon").value(20.0));
    }

    @Test
    public void GetFavouritePlaceById_ReturnNotFound() throws Exception {
        UUID placeId = UUID.randomUUID();

        when(favouritePlaceService.getFavouritePlaceById(any(Jwt.class), eq(placeId)))
                .thenThrow(new ResourceNotFoundException("Favourite place not found"));

        mockMvc.perform(get("/fav-place/{id}", placeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Favourite place not found"));
    }

}
