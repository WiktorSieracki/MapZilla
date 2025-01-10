package com.mapzilla.mapzilla.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String coordinates;
    private int score;
    //Jedna lokalizacja należy do jednego użytkownika
    //Jezeli dwoch uzytkowników miałoby te same kordy to mogą
    //mieć różny score ze względu na inne preferencje
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    //Lokalizacja może mieć wiele udogodnień
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Amenity> amenities;
}
