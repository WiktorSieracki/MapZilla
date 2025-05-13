package com.mapzilla.backend.feature.history.utils;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class MapPoint {
    @Id
    Long id;
    String type;
    BigDecimal lat;
    BigDecimal lon;
    @OneToMany
    List<Tag> tags;
}
