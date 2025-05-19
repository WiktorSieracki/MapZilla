package com.mapzilla.backend.feature.history.utils;

import com.mapzilla.backend.feature.map.enums.PlaceType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    BigDecimal score;
    BigDecimal lat;
    BigDecimal lon;
    @ElementCollection
    Set<PlaceType> availablePlaces;
    @ElementCollection
    Set<PlaceType> notAvailablePlaces;
    //TODO: think if we really need that here
//    @ElementCollection
//    List<MapPoint> places = new ArrayList<>();
}
