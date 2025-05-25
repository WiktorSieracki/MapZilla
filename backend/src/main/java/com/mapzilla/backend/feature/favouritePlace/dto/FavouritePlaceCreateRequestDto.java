package com.mapzilla.backend.feature.favouritePlace.dto;

import com.mapzilla.backend.feature.map.enums.PlaceType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
public class FavouritePlaceCreateRequestDto {
    BigDecimal score;
    BigDecimal lat;
    BigDecimal lon;
    Set<PlaceType> availablePlaces;
    Set<PlaceType> notAvailablePlaces;
    Optional<Set<UUID>> labels;
}
