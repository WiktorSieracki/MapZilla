package com.mapzilla.backend.feature.label.controller;

import com.mapzilla.backend.feature.label.service.LabelService;
import com.mapzilla.backend.response.ApiResponse;
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
    public ApiResponse<> getAllLabels() {
        return new ApiResponse()
    }

}
