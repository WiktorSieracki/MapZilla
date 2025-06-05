package com.mapzilla.backend.history.controller;

import com.mapzilla.backend.feature.history.controller.HistoryController;
import com.mapzilla.backend.feature.history.dto.HistoryResponseDto;
import com.mapzilla.backend.feature.history.service.HistoryService;
import com.mapzilla.backend.feature.user.service.UserSynchronizer;
import com.mapzilla.backend.history.mockData.HistoryMockData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = HistoryController.class)
@AutoConfigureMockMvc(addFilters = false)
class HistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private HistoryService historyService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserSynchronizer userSynchronizer;


    private Jwt jwt;

    @BeforeEach
    void setUp() {
        jwt = Jwt.withTokenValue("fake-token")
                .header("alg", "none")
                .claim("sub", "user123")
                .build();

        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(jwt);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void getUserHistory_ReturnsSuccess() throws Exception {
        HistoryResponseDto mockDto = HistoryMockData.mockHistoryResponseDto();

        when(historyService.getUserHistory(any(Jwt.class))).thenReturn(mockDto);

        mockMvc.perform(get("/history")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("RESPONSE_SUCCESSFUL"))
                .andExpect(jsonPath("$.message").value("Successfully fetched user's history"))
                .andExpect(jsonPath("$.data.id").value(mockDto.getId().toString()))
                .andExpect(jsonPath("$.data.places").isArray())
                .andExpect(jsonPath("$.data.places.length()").value(mockDto.getPlaces().size()));
    }

    @Test
    void getUserHistory_ThrowsException_Returns500() throws Exception {
        when(historyService.getUserHistory(any(Jwt.class))).thenThrow(new RuntimeException("User history not found"));

        mockMvc.perform(get("/history")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("User history not found"));
    }
}
