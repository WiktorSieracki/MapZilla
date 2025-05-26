package com.mapzilla.backend.feature.map.dto;

import com.mapzilla.backend.feature.map.utils.MapPoint;
import com.mapzilla.backend.feature.map.enums.PlaceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MapResponseDto {
    BigDecimal score;
    BigDecimal lat;
    BigDecimal lon;
    Set<PlaceType> availablePlaces;
    Set<PlaceType> notAvailablePlaces;
    List<MapPoint> places;

    public static MapResponseDto from(@NonNull MapResponse mapResponseDto) {
        return new MapResponseDto(
                mapResponseDto.getScore(),
                mapResponseDto.getLat(),
                mapResponseDto.getLon(),
                mapResponseDto.getAvailablePlaces(),
                mapResponseDto.getNotAvailablePlaces(),
                mapResponseDto.getPlaces()
        );
    }
}
