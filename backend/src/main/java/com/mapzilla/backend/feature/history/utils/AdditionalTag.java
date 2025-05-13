package com.mapzilla.backend.feature.history.utils;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class AdditionalTag {
    String key;
    String value;
}
