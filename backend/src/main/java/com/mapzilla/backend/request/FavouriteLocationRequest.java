package com.mapzilla.backend.request;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class FavouriteLocationRequest {
    @NotBlank(message = "User ID is required and cannot be empty")
    private String userId;

    @NotNull(message = "Score is required")
    @Min(value = 0, message = "Score must be greater than 0")
    private BigDecimal score;

    @NotNull(message = "Latitude is required")
    private BigDecimal lat;

    @NotNull(message = "Longitude is required")
    private BigDecimal lon;

    private List<String> availablePlaces;
    private List<String> notAvailablePlaces;
}
