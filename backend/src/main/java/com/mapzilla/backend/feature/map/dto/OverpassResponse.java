package com.mapzilla.backend.feature.map.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mapzilla.backend.feature.map.utils.MapPoint;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class OverpassResponse {
    @JsonProperty("elements")
    List<MapPoint> mapPoints;
}
