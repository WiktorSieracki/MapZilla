package com.mapzilla.backend.feature.map.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Member {
    @JsonProperty("type")
    private String type;

    @JsonProperty("ref")
    private long ref;

    @JsonProperty("role")
    private String role;
}
