package com.mapzilla.backend.feature.map.service;

import com.mapzilla.backend.feature.history.utils.Geometry;
import com.mapzilla.backend.feature.history.utils.MapPoint;
import com.mapzilla.backend.feature.history.utils.Node;
import com.mapzilla.backend.feature.history.utils.Relation;
import com.mapzilla.backend.feature.history.utils.Way;
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
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OverpassMapper {
    public List<MapPoint> toMapPoints(OverpassResponse resp) {
        return resp.getMapPoints().stream()
                .map(el -> {
                    if(Objects.equals(el.getType(), "node") && el instanceof Node node) {
                        Node newNode = new Node();
                        newNode.setType(node.getType());
                        newNode.setTags(node.getTags());
                        newNode.setLat(node.getLat());
                        newNode.setLon(node.getLon());
                        return newNode;
                    } else if(Objects.equals(el.getType(), "way") && el instanceof Way way) {
                        Way newWay = new Way();
                        newWay.setType(way.getType());
                        newWay.setGeometry(way.getGeometry());
                        newWay.setTags(way.getTags());
                        return newWay;
                    } else if (Objects.equals(el.getType(), "relation") && el instanceof Relation relation) {
                        Relation newRelation = new Relation();
                        newRelation.setType(relation.getType());
                        newRelation.setTags(relation.getTags());
                        newRelation.setMembers(relation.getMembers());
                        return newRelation;
                    } else {
                        MapPoint mapPoint = new MapPoint();
                        mapPoint.setType(el.getType());
                        mapPoint.setTags(el.getTags());
                        return mapPoint;
                    }
                })
                .toList();
    }
}
