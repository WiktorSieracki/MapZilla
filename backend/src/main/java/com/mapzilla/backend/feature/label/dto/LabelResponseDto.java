package com.mapzilla.backend.feature.label.dto;

import com.mapzilla.backend.feature.label.enums.Color;
import com.mapzilla.backend.feature.label.model.Label;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabelResponseDto {
    UUID id;
    String name;
    Color color;

    public static LabelResponseDto from(Label label) {
        return new LabelResponseDto(
                label.getId(),
                label.getName(),
                label.getColor()
        );
    }
}
