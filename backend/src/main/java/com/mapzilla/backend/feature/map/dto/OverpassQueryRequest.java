package com.mapzilla.backend.feature.map.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OverpassQueryRequest {
    private String square;
    private String centerX;
    private String centerY;
    private String selectedPlaces;
}
