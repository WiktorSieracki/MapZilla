package com.mapzilla.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FavouriteLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private BigDecimal score;
    private BigDecimal lat;
    private BigDecimal lon;

    @ElementCollection
    private List<String> availablePlaces;

    @ElementCollection
    private List<String> notAvailablePlaces;
}
