package com.mapzilla.backend.feature.map.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Way extends MapPoint {
    @JsonProperty("geometry")
    private List<Geometry> geometry;
}
