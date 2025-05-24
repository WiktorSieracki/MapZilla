package com.mapzilla.backend.feature.favouritePlace.dto;

import com.mapzilla.backend.feature.favouritePlace.model.FavouritePlace;
import com.mapzilla.backend.feature.label.model.Label;
import com.mapzilla.backend.feature.map.enums.PlaceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class FavouritePlaceResponseDto {
    UUID id;
    BigDecimal score;
    BigDecimal lat;
    BigDecimal lon;
    Set<PlaceType> availablePlaces;
    Set<PlaceType> notAvailablePlaces;
    Set<Label> labels;

    public static FavouritePlaceResponseDto from(FavouritePlace favouritePlace) {
        return new FavouritePlaceResponseDto(
                favouritePlace.getId(),
                favouritePlace.getScore(),
                favouritePlace.getLat(),
                favouritePlace.getLon(),
                favouritePlace.getAvailablePlaces(),
                favouritePlace.getNotAvailablePlaces(),
                favouritePlace.getLabels()
        );
    }
}
