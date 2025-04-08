package com.mapzilla.backend.feature.favouritePlace.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavouritePlace {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private BigDecimal lat;
    private BigDecimal score;
    private BigDecimal lon;
    private String userId;

    @ElementCollection
    private Set<String> availablePlaces;

    @ElementCollection
    private Set<String> unavailablePlaces;

}
