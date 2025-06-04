package com.mapzilla.backend.feature.label.dto;


import com.mapzilla.backend.feature.label.enums.Color;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LabelCreateDto {
    @NotEmpty(message = "name can not be empty")
    String name;

    Color color;
}
