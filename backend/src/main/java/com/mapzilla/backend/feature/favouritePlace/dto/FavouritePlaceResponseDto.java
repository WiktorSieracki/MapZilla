package com.mapzilla.backend.feature.favouritePlace.dto;

import com.mapzilla.backend.feature.favouritePlace.model.FavouritePlace;
import com.mapzilla.backend.feature.map.enums.PlaceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class FavouritePlaceResponseDto {
    BigDecimal score;
    BigDecimal lat;
    BigDecimal lon;
    Set<PlaceType> availablePlaces;
    Set<PlaceType> notAvailablePlaces;

    public static FavouritePlaceResponseDto from(FavouritePlace favouritePlace) {
        return new FavouritePlaceResponseDto(
                favouritePlace.getScore(),
                favouritePlace.getLat(),
                favouritePlace.getLon(),
                favouritePlace.getAvailablePlaces(),
                favouritePlace.getNotAvailablePlaces()
        );
    }
}
