package com.mapzilla.backend.feature.history.utils;

import com.mapzilla.backend.feature.map.dto.MapResponse;
import com.mapzilla.backend.feature.map.enums.PlaceType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;
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

    public Location(BigDecimal score, BigDecimal lat, BigDecimal lon,
                    Set<PlaceType> availablePlaces, Set<PlaceType> notAvailablePlaces) {
        this.score = score;
        this.lat = lat;
        this.lon = lon;
        this.availablePlaces = availablePlaces;
        this.notAvailablePlaces = notAvailablePlaces;
    }

    public static Location from(@NonNull MapResponse mapResponse) {
        return new Location(
          mapResponse.getScore(),
          mapResponse.getLat(),
          mapResponse.getLon(),
          mapResponse.getAvailablePlaces(),
          mapResponse.getNotAvailablePlaces()
        );
    }
}
