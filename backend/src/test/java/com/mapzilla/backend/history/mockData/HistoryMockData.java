package com.mapzilla.backend.history.mockData;

import com.mapzilla.backend.feature.history.dto.HistoryResponseDto;
import com.mapzilla.backend.feature.label.model.Label;
import com.mapzilla.backend.feature.map.utils.Location;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class HistoryMockData {

    public static HistoryResponseDto mockHistoryResponseDto() {
        UUID historyId = UUID.randomUUID();

        Location location1 = new Location();
        location1.setId(UUID.randomUUID());
        location1.setScore(BigDecimal.valueOf(1.0));
        location1.setLat(BigDecimal.valueOf(53.74));
        location1.setLon(BigDecimal.valueOf(20.48));

        Location location2 = new Location();
        location2.setId(UUID.randomUUID());
        location2.setScore(BigDecimal.valueOf(0.0));
        location2.setLat(BigDecimal.valueOf(-53.74));
        location2.setLon(BigDecimal.valueOf(20.48));

        Set<Label> labels = Set.of();

        return new HistoryResponseDto(historyId, List.of(location1, location2), labels);
    }
}
