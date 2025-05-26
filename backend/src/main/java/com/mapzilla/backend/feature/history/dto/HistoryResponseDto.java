package com.mapzilla.backend.feature.history.dto;

import com.mapzilla.backend.feature.history.model.History;
import com.mapzilla.backend.feature.label.model.Label;
import com.mapzilla.backend.feature.map.utils.Location;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
public class HistoryResponseDto {
    private UUID id;
    private List<Location> places;
    private Set<Label> labels;

    public static HistoryResponseDto from(@NotNull History history) {
        return new HistoryResponseDto(
                history.getId(),
                history.getPlaces(),
                history.getLabels()
        );
    }
}
