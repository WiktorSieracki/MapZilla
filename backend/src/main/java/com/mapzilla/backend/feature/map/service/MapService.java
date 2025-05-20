package com.mapzilla.backend.feature.map.service;

import com.mapzilla.backend.feature.map.dto.MapResponseDto;
import com.mapzilla.backend.feature.map.dto.OverpassApiRequest;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public interface MapService {
    MapResponseDto getMap(Jwt jwt, OverpassApiRequest request);
}
