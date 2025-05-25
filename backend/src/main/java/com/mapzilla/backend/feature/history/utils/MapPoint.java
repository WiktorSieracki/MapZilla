package com.mapzilla.backend.feature.history.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true,
        defaultImpl = MapPoint.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Node.class, name = "node"),
        @JsonSubTypes.Type(value = Way.class, name = "way"),
        @JsonSubTypes.Type(value = Relation.class, name = "relation")
})
@Getter
@Setter
public class MapPoint {
    @JsonProperty("type")
    String type;
    @JsonProperty("tags")
    Map<String,String> tags;
}
