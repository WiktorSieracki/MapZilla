package com.mapzilla.backend.feature.history.utils;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;


@Getter
@Setter
public class MapPoint {
    String type;
    BigDecimal lat;
    BigDecimal lon;
    Map<String,String> tags;
}
