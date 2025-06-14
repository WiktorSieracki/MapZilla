package com.mapzilla.backend.feature.favouritePlace.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mapzilla.backend.feature.favouritePlace.dto.FavouritePlaceUpdateDto;
import com.mapzilla.backend.feature.label.model.Label;
import com.mapzilla.backend.feature.map.enums.PlaceType;
import com.mapzilla.backend.feature.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FavouritePlace {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
    private BigDecimal score;
    private BigDecimal lat;
    private BigDecimal lon;
    @ManyToMany
    private Set<Label> labels = new HashSet<>();

    @ElementCollection
    private Set<PlaceType> availablePlaces;

    @ElementCollection
    private Set<PlaceType> notAvailablePlaces;

    public void update(FavouritePlaceUpdateDto favouritePlaceUpdateDto) {
        this.score = favouritePlaceUpdateDto.getScore();
        this.lat = favouritePlaceUpdateDto.getLat();
        this.lon = favouritePlaceUpdateDto.getLon();
        this.availablePlaces = favouritePlaceUpdateDto.getAvailablePlaces();
        this.notAvailablePlaces = favouritePlaceUpdateDto.getNotAvailablePlaces();
        if (favouritePlaceUpdateDto.getLabels() != null) {
            this.labels.clear();
            this.labels.addAll(favouritePlaceUpdateDto.getLabels());
        }
    }
}
