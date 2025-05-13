package com.mapzilla.backend.feature.history.utils;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.List;
import java.util.Map;

@Entity
public class Tag {
    @Id
    @GeneratedValue
    Long id;
    String name;
    String amenity;
    String leisure;
    String addrcity;
    @ElementCollection
    List<AdditionalTag> additional;
}
