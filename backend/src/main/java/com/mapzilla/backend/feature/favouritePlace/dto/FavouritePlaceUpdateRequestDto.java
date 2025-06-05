package com.mapzilla.backend.feature.favouritePlace.dto;

import com.mapzilla.backend.feature.label.model.Label;
import com.mapzilla.backend.feature.map.enums.PlaceType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Data
public class FavouritePlaceUpdateRequestDto {
    //TODO- add validation
    BigDecimal score;
    BigDecimal lat;
    BigDecimal lon;
    Set<PlaceType> availablePlaces;
    Set<PlaceType> notAvailablePlaces;
    Optional<Set<UUID>> labels;
}
