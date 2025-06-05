package com.mapzilla.backend.labels.mockdata;

import com.mapzilla.backend.feature.label.dto.LabelCreateDto;
import com.mapzilla.backend.feature.label.dto.LabelResponseDto;
import com.mapzilla.backend.feature.label.dto.LabelUpdateDto;
import com.mapzilla.backend.feature.label.enums.Color;

import java.util.UUID;

public class LabelMockData {

    public static LabelResponseDto createMockResponseDto() {
        LabelResponseDto dto = new LabelResponseDto();
        dto.setId(UUID.randomUUID());
        dto.setName("Test Label");
        dto.setColor(Color.BLUE);
        return dto;
    }

    public static LabelCreateDto createValidLabelCreateDto() {
        LabelCreateDto dto = new LabelCreateDto();
        dto.setName("Valid Label");
        dto.setColor(Color.GREEN);
        return dto;
    }

    public static LabelResponseDto createValidLabelResponseDto() {
        LabelResponseDto dto = new LabelResponseDto();
        dto.setId(UUID.randomUUID());
        dto.setName("Test Label");
        dto.setColor(Color.BLUE);
        return dto;
    }

    public static LabelCreateDto createLabelCreateDtoWithEmptyName() {
        LabelCreateDto dto = new LabelCreateDto();
        dto.setName("");
        dto.setColor(Color.BLUE);
        return dto;
    }

    public static String createInvalidColorJson() {
        return """
        {
            "name": "Test Label",
            "color": "BLUEs"
        }
        """;
    }

    public static LabelUpdateDto createValidLabelUpdateDto() {
        LabelUpdateDto dto = new LabelUpdateDto();
        dto.setName("Updated Label");
        dto.setColor(Color.GREEN);
        return dto;
    }

    public static LabelUpdateDto createInvalidLabelUpdateDtoWithEmptyName() {
        LabelUpdateDto dto = new LabelUpdateDto();
        dto.setName("");
        dto.setColor(Color.RED);
        return dto;
    }
    public static String createInvalidUpdateColorEnumJson() {
        return """
        {
            "name": "Updated Label",
            "color": "BAD_COLOR"
        }
        """;
    }

}
