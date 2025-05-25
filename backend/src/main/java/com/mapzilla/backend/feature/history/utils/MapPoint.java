package com.mapzilla.backend.feature.history.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Getter
@Setter
public class MapPoint {
    @JsonProperty("type")
    String type;
    @JsonProperty("lat")
    BigDecimal lat;
    @JsonProperty("lon")
    BigDecimal lon;
    @JsonProperty("geometry")
    private List<Geometry> geometry;
    @JsonProperty("tags")
    Map<String,String> tags;
}
