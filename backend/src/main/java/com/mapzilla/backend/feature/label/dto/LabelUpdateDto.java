package com.mapzilla.backend.feature.label.dto;

import com.mapzilla.backend.feature.label.enums.Color;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LabelUpdateDto {
    @NotEmpty(message = "name can not be empty")
    String name;

    Color color;
}
