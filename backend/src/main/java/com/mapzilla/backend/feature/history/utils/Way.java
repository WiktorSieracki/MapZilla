package com.mapzilla.backend.feature.history.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Way extends MapPoint {
    @JsonProperty("geometry")
    List<Geometry> geometry;
}
