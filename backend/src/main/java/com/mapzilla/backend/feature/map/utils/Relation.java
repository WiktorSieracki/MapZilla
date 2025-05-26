package com.mapzilla.backend.feature.map.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Relation extends MapPoint {
    @JsonProperty("id")
    private long id;

    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("version")
    private int version;

    @JsonProperty("changeset")
    private long changeset;

    @JsonProperty("user")
    private String user;

    @JsonProperty("uid")
    private long uid;

    @JsonProperty("members")
    private List<Member> members;

}
