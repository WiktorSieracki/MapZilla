package com.mapzilla.backend.feature.favouritePlace.service;

import com.mapzilla.backend.feature.favouritePlace.dto.FavouritePlaceCreateDto;
import com.mapzilla.backend.feature.favouritePlace.dto.FavouritePlaceResponseDto;
import com.mapzilla.backend.feature.favouritePlace.dto.FavouritePlaceUpdateDto;
import com.mapzilla.backend.feature.favouritePlace.dto.FavouritePlaceUpdateRequestDto;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;
import java.util.UUID;

public interface FavouritePlaceService {

    FavouritePlaceResponseDto addFavouritePlace(Jwt jwt, FavouritePlaceCreateDto favouritePlaceCreateDto);

    List<FavouritePlaceResponseDto> getAllFavouritePlaces(Jwt jwt);

    FavouritePlaceResponseDto getFavouritePlaceById(Jwt jwt, UUID id);

    void deleteFavouriteLocationById(Jwt jwt, UUID id);

    FavouritePlaceResponseDto updateFavouritePlaceById(Jwt jwt, UUID id, FavouritePlaceUpdateRequestDto favouritePlaceUpdateRequestDto);
}
