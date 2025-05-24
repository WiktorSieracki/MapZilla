package com.mapzilla.backend.feature.label.dto;

import com.mapzilla.backend.feature.label.enums.Color;
import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class LabelUpdateDto {
    @Nullable
    String name;
    @Nullable
    Color color;
}
