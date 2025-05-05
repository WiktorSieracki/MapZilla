package com.mapzilla.backend.feature.map.dto;

import com.mapzilla.backend.feature.map.enums.PlaceType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OverpassApiRequest {
    private double lat;
    private double lon;
    private int radius = 1200; // domyślny radius w metrach
    private List<PlaceType> types;
}
