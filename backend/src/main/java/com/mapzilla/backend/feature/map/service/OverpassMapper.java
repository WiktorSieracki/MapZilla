package com.mapzilla.backend.feature.map.service;

import com.mapzilla.backend.feature.history.utils.Geometry;
import com.mapzilla.backend.feature.history.utils.MapPoint;
import com.mapzilla.backend.feature.map.dto.OverpassResponse;
import com.mapzilla.backend.feature.map.enums.PlaceType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OverpassMapper {
    public List<MapPoint> toMapPoints(OverpassResponse resp) {
        return resp.getMapPoints().stream()
                .map(el -> {
                    MapPoint p = new MapPoint();
                    p.setType(el.getType());
                    p.setLat(el.getLat());
                    p.setLon(el.getLon());
                    p.setGeometry(el.getGeometry());
                    p.setTags(el.getTags());
                    return p;
                })
                .toList();
    }
}
