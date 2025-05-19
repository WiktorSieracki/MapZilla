package com.mapzilla.backend.feature.history.utils;

import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Getter
@Setter
//@Embeddable
public class MapPoint {
    String type;
    BigDecimal lat;
    BigDecimal lon;
//    @ElementCollection
    Map<String,String> tags;
//    @Convert(converter = TagsConverter.class)
//    List<AdditionalTag> tags;
}
