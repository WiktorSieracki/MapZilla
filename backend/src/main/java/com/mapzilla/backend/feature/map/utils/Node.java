package com.mapzilla.backend.feature.map.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Node extends MapPoint {
    @JsonProperty("lat")
    private BigDecimal lat;
    @JsonProperty("lon")
    private BigDecimal lon;
}
