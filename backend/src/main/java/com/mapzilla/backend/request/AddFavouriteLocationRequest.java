package com.mapzilla.backend.request;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class AddFavouriteLocationRequest {
    private String userId;

    @NotNull(message = "Score is required")
    @Min(value = 0, message = "Score must be greater than 0")
    private BigDecimal score;

    @NotNull(message = "Latitude is required")
    @Min(value = 0, message = "Lat must be greater than 0")
    private BigDecimal lat;

    @NotNull(message = "Longitude is required")
    @Min(value = 0, message = "Lon must be greater than 0")
    private BigDecimal lon;

    @NotNull(message = "List of availablePlaces is required!")
    private List<String> availablePlaces;

    @NotNull(message = "List of notAvailablePlaces is required!")
    private List<String> notAvailablePlaces;
}
