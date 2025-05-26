package com.mapzilla.backend.feature.history.controller;

import com.mapzilla.backend.feature.history.dto.HistoryResponseDto;
import com.mapzilla.backend.feature.history.model.History;
import com.mapzilla.backend.feature.history.service.HistoryService;
import com.mapzilla.backend.feature.util.dto.ApiResponse;
import com.mapzilla.backend.feature.util.enums.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping
    public ApiResponse<HistoryResponseDto> getUserHistory(@AuthenticationPrincipal Jwt jwt) {
        return new ApiResponse<>(
                SuccessCode.RESPONSE_SUCCESSFUL,
                "Successfully fetched user's history",
                historyService.getUserHistory(jwt)
        );
    }
}
