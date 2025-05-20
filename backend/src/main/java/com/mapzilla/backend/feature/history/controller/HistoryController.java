package com.mapzilla.backend.feature.history.controller;

import com.mapzilla.backend.feature.history.model.History;
import com.mapzilla.backend.feature.history.service.HistoryService;
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
    public History getUserHistory(@AuthenticationPrincipal Jwt jwt) {
        return historyService.getUserHistory(jwt);
    }
}
