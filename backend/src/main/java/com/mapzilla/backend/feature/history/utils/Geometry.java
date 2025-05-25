package com.mapzilla.backend.feature.history.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Geometry {
    private BigDecimal lat;
    private BigDecimal lon;
}
