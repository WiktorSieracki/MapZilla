package com.mapzilla.backend.feature.history.service;

import com.mapzilla.backend.feature.history.dto.HistoryResponseDto;
import com.mapzilla.backend.feature.history.model.History;
import com.mapzilla.backend.feature.map.utils.Location;
import org.springframework.security.oauth2.jwt.Jwt;

public interface HistoryService {
    void addToHistory(Jwt jwt, Location location);

    HistoryResponseDto getUserHistory(Jwt jwt);

}
