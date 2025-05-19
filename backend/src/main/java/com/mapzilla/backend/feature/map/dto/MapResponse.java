package com.mapzilla.backend.feature.map.dto;

import com.mapzilla.backend.feature.history.utils.MapPoint;
import com.mapzilla.backend.feature.map.enums.PlaceType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
public class MapResponse {
    BigDecimal score;
    BigDecimal lat;
    BigDecimal lon;
    Set<PlaceType> availablePlaces;
    Set<PlaceType> notAvailablePlaces;
    List<MapPoint> places;
}
