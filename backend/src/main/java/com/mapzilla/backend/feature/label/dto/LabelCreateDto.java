package com.mapzilla.backend.feature.label.dto;


import com.mapzilla.backend.feature.label.enums.Color;
import lombok.Data;

@Data
public class LabelCreateDto {
    String name;
    Color color;
}
