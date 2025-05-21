package com.mapzilla.backend.feature.label.controller;

import com.mapzilla.backend.feature.label.dto.LabelResponseDto;
import com.mapzilla.backend.feature.label.service.LabelService;
import com.mapzilla.backend.feature.util.dto.ApiResponse;
import com.mapzilla.backend.feature.util.enums.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/labels")
@RequiredArgsConstructor
public class LabelController {
    private final LabelService labelService;

    @GetMapping
    public ApiResponse<LabelResponseDto> getAllLabels() {
        return new ApiResponse(
                SuccessCode.RESPONSE_SUCCESSFUL,
                "Successfully fetched all labels",
                labelService.getAllLabels());
    }

}
