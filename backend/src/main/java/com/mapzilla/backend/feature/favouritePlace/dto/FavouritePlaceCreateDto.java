package com.mapzilla.backend.feature.favouritePlace.dto;

import com.mapzilla.backend.feature.map.enums.PlaceType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class FavouritePlaceCreateDto {
    @NotNull(message = "Score is required")
    @Min(value = 0, message = "Score must be greater than 0")
    BigDecimal score;


    @NotNull(message = "Latitude is required")
    @Min(value = 0, message = "Lat must be greater than 0")
    BigDecimal lat;

    @NotNull(message = "Longitude is required")
    @Min(value = 0, message = "Lon must be greater than 0")
    BigDecimal lon;

    @NotNull(message = "List of availablePlaces is required!")
    Set<PlaceType> availablePlaces;

    @NotNull(message = "List of notAvailablePlaces is required!")
    Set<PlaceType> notAvailablePlaces;
}
