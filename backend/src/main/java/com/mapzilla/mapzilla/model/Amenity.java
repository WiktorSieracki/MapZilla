package com.mapzilla.mapzilla.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Amenity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String distance;
    private boolean isNearbyWalkingDistance;
    //każdy obiekt przypisany do jednej lokalizacji
    //jeśli dwóch użytkowników ma ten sam sklep no to o
    // np. o innych odleglosciach więc mamy dwa rekordy
    // z tym samym sklepem
    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

}
