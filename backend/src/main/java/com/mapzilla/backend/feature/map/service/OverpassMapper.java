package com.mapzilla.backend.feature.map.service;

import com.mapzilla.backend.feature.map.utils.MapPoint;
import com.mapzilla.backend.feature.map.utils.Node;
import com.mapzilla.backend.feature.map.utils.Relation;
import com.mapzilla.backend.feature.map.utils.Way;
import com.mapzilla.backend.feature.map.dto.OverpassResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class OverpassMapper {
    public List<MapPoint> toMapPoints(OverpassResponse resp) {
        return resp.getMapPoints().stream()
                .map(el -> {
                    switch (el) {
                        case Node node when Objects.equals(el.getType(), "node") -> {
                            return getNode(node);
                        }
                        case Way way when Objects.equals(el.getType(), "way") -> {
                            return getWay(way);
                        }
                        case Relation relation when Objects.equals(el.getType(), "relation") -> {
                            return getRelation(relation);
                        }
                        default -> {
                            MapPoint mapPoint = new MapPoint();
                            mapPoint.setType(el.getType());
                            mapPoint.setTags(el.getTags());
                            return mapPoint;
                        }
                    }
                })
                .toList();
    }

    private static @NotNull Relation getRelation(Relation relation) {
        Relation newRelation = new Relation();
        newRelation.setType(relation.getType());
        newRelation.setId(relation.getId());
        newRelation.setTags(relation.getTags());
        newRelation.setId(relation.getId());
        newRelation.setTimestamp(relation.getTimestamp());
        newRelation.setVersion(relation.getVersion());
        newRelation.setChangeset(relation.getChangeset());
        newRelation.setUser(relation.getUser());
        newRelation.setUid(relation.getUid());
        newRelation.setMembers(relation.getMembers());
        return newRelation;
    }

    private static @NotNull Node getNode(Node node) {
        Node newNode = new Node();
        newNode.setType(node.getType());
        newNode.setId(node.getId());
        newNode.setTags(node.getTags());
        newNode.setLat(node.getLat());
        newNode.setLon(node.getLon());
        return newNode;
    }

    private static @NotNull Way getWay(Way way) {
        Way newWay = new Way();
        newWay.setType(way.getType());
        newWay.setId(way.getId());
        newWay.setTags(way.getTags());
        newWay.setGeometry(way.getGeometry());
        return newWay;
    }
}
