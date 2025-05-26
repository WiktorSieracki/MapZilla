package com.mapzilla.backend.feature.history.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class Node extends MapPoint {
    @JsonProperty("lat")
    BigDecimal lat;
    @JsonProperty("lon")
    BigDecimal lon;
}
