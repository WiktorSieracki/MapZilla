package com.mapzilla.backend.feature.map.dto;

import com.mapzilla.backend.feature.map.enums.PlaceType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OverpassApiRequest {

    @NotNull(message = "Latitude is required")
    @Min(value = 0, message = "Lat must be greater than 0")
    private double lat;

    @NotNull(message = "Longitude is required")
    @Min(value = 0, message = "Lon must be greater than 0")
    private double lon;

    @NotNull(message = "Longitude is required")
    @Min(value = 0, message = "Lon must be greater than 0")
    private int radius = 1200;

    @NotNull(message = "List of notAvailablePlaces is required!")
    private List<PlaceType> types;
}
