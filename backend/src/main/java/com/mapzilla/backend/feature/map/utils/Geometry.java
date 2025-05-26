package com.mapzilla.backend.feature.map.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class Geometry {
    private BigDecimal lat;
    private BigDecimal lon;
}
