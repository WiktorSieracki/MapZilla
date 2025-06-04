package com.mapzilla.backend.feature.label.controller;

import com.mapzilla.backend.feature.label.dto.LabelCreateDto;
import com.mapzilla.backend.feature.label.dto.LabelResponseDto;
import com.mapzilla.backend.feature.label.dto.LabelUpdateDto;
import com.mapzilla.backend.feature.label.service.LabelService;
import com.mapzilla.backend.feature.util.dto.ApiResponse;
import com.mapzilla.backend.feature.util.enums.SuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/labels")
@RequiredArgsConstructor
public class LabelController {
    private final LabelService labelService;

    @GetMapping
    public ApiResponse<Set<LabelResponseDto>> getAllLabels() {
        return new ApiResponse(
                SuccessCode.RESPONSE_SUCCESSFUL,
                "Successfully fetched all labels",
                labelService.getAllLabels());
    }

    @GetMapping("/{id}")
    public ApiResponse<LabelResponseDto> getLabelById(@PathVariable UUID id) {
        return new ApiResponse<>(
                SuccessCode.RESPONSE_SUCCESSFUL,
                "Successfully fetched label by id" + id,
                labelService.getLabelById(id)
        );
    }

    @PostMapping
    public ApiResponse<LabelResponseDto> createLabel(@RequestBody @Valid LabelCreateDto labelCreateDto) {
        return new ApiResponse<>(
                SuccessCode.RESOURCE_CREATED,
                "Successfully created label",
                labelService.createLabel(labelCreateDto)
        );
    }

    @PatchMapping("/{id}")
    public ApiResponse<LabelResponseDto> updateLabel(@PathVariable UUID id, @RequestBody LabelUpdateDto labelUpdateDto) {
        return new ApiResponse<>(
                SuccessCode.RESOURCE_UPDATED,
                "Successfully updated label with id " + id,
                labelService.updateLabel(id, labelUpdateDto)
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteLabel(@PathVariable UUID id) {
        labelService.deleteLabel(id);

        return new ApiResponse<>(
                SuccessCode.RESPONSE_SUCCESSFUL,
                "Successfully deleted label",
                null
        );
    }

}
