package com.mapzilla.backend.feature.history.utils;

import java.math.BigDecimal;
import java.util.List;

public class Node {
    Long id;
    String type;
    BigDecimal lat;
    BigDecimal lon;
    List<Tag> tags;
}
