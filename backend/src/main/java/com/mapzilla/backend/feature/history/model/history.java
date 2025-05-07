package com.mapzilla.backend.feature.history.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.util.UUID;

@Entity
public class history {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

//    @OneToOne
    String userId;

//    String Set<Obj>;
}
