package com.mapzilla.backend.feature.favouritePlace.dto;

import com.mapzilla.backend.feature.favouritePlace.model.FavouritePlace;
import com.mapzilla.backend.feature.label.model.Label;
import com.mapzilla.backend.feature.map.enums.PlaceType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
public class FavouritePlaceUpdateDto {
    BigDecimal score;
    BigDecimal lat;
    BigDecimal lon;
    Set<PlaceType> availablePlaces;
    Set<PlaceType> notAvailablePlaces;
    Set<Label> labels;

    public static FavouritePlaceUpdateDto from(FavouritePlace favouritePlace, Set<Label> labels) {
        return new FavouritePlaceUpdateDto(
                favouritePlace.getScore(),
                favouritePlace.getLat(),
                favouritePlace.getLon(),
                favouritePlace.getAvailablePlaces(),
                favouritePlace.getNotAvailablePlaces(),
                labels
        );
    }
}
