package com.mapzilla.backend.request;
import jakarta.validation.constraints.*;


import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class UpdateFavouriteLocationRequest {
    @Min(value = 0, message = "Score must be greater than 0")
    private BigDecimal score;

    @Min(value = 0, message = "Lat must be greater than 0")
    private BigDecimal lat;

    @Min(value = 0, message = "Lon must be greater than 0")
    private BigDecimal lon;

    private List<String> availablePlaces;

    private List<String> notAvailablePlaces;

}
