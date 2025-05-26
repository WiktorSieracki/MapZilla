package com.mapzilla.backend.feature.history.utils;

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
