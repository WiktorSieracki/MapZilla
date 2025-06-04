package com.mapzilla.backend.favouritePlace.controller;

import com.mapzilla.backend.feature.favouritePlace.dto.FavouritePlaceCreateDto;
import com.mapzilla.backend.feature.favouritePlace.dto.FavouritePlaceResponseDto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class FavouritePlaceMockData {

    public static FavouritePlaceCreateDto createValidFavouritePlaceCreateDto() {
        FavouritePlaceCreateDto dto = new FavouritePlaceCreateDto();
        dto.setScore(BigDecimal.valueOf(10.0));
        dto.setLat(BigDecimal.valueOf(50.0));
        dto.setLon(BigDecimal.valueOf(20.0));
        dto.setAvailablePlaces(new HashSet<>());
        dto.setNotAvailablePlaces(new HashSet<>());
        return dto;
    }


    public static FavouritePlaceCreateDto createInvalidScoreDto() {
        FavouritePlaceCreateDto dto = createValidFavouritePlaceCreateDto();
        dto.setScore(BigDecimal.valueOf(-5.0));
        return dto;
    }

    public static FavouritePlaceCreateDto createInvalidLatDto() {
        FavouritePlaceCreateDto dto = createValidFavouritePlaceCreateDto();
        dto.setLat(BigDecimal.valueOf(-100.0));
        return dto;
    }

    public static FavouritePlaceCreateDto createInvalidLonDto() {
        FavouritePlaceCreateDto dto = createValidFavouritePlaceCreateDto();
        dto.setLon(BigDecimal.valueOf(-200.0));
        return dto;
    }
    public static FavouritePlaceResponseDto createMockResponseDto() {
        FavouritePlaceResponseDto dto = new FavouritePlaceResponseDto();
        dto.setId(UUID.randomUUID());
        dto.setScore(BigDecimal.valueOf(10.0));
        dto.setLat(BigDecimal.valueOf(50.0));
        dto.setLon(BigDecimal.valueOf(20.0));
        dto.setAvailablePlaces(Set.of());
        dto.setNotAvailablePlaces(Set.of());
        return dto;
    }

}
