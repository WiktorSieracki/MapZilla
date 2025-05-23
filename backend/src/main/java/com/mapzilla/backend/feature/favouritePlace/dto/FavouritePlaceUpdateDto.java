package com.mapzilla.backend.feature.favouritePlace.dto;

import com.mapzilla.backend.feature.map.enums.PlaceType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class FavouritePlaceUpdateDto {
    BigDecimal score;
    BigDecimal lat;
    BigDecimal lon;
    Set<PlaceType> availablePlaces;
    Set<PlaceType> notAvailablePlaces;
}
