package com.mapzilla.backend.labels.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapzilla.backend.exceptions.ResourceNotFoundException;
import com.mapzilla.backend.feature.label.controller.LabelController;
import com.mapzilla.backend.feature.label.dto.LabelCreateDto;
import com.mapzilla.backend.feature.label.dto.LabelResponseDto;
import com.mapzilla.backend.feature.label.dto.LabelUpdateDto;
import com.mapzilla.backend.feature.label.service.LabelService;
import com.mapzilla.backend.feature.user.service.UserSynchronizer;
import com.mapzilla.backend.labels.mockdata.LabelMockData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.Set;
import java.util.UUID;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = LabelController.class)
@AutoConfigureMockMvc(addFilters = false)
class LabelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LabelService labelService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserSynchronizer userSynchronizer;


//    ==================Getting all labels=====================
    @Test
    void getAllLabels_ReturnSuccessWithData() throws Exception {
        Set<LabelResponseDto> mockLabels = Set.of(
                LabelMockData.createMockResponseDto(),
                LabelMockData.createMockResponseDto()
        );

        when(labelService.getAllLabels()).thenReturn(mockLabels);

        mockMvc.perform(get("/labels")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Successfully fetched all labels"))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(2));
    }

// ==================== getting label by id=========================
    @Test
    void getLabelById_ReturnSuccess() throws Exception {
        UUID labelId = UUID.randomUUID();
        LabelResponseDto mockLabel = new LabelResponseDto();
        mockLabel.setId(labelId);
        mockLabel.setName("Sample Label");
        mockLabel.setColor(com.mapzilla.backend.feature.label.enums.Color.YELLOW);

        when(labelService.getLabelById(labelId)).thenReturn(mockLabel);

        mockMvc.perform(get("/labels/{id}", labelId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getLabelById_ReturnSuccessWithCorrectResponse() throws Exception {
        UUID labelId = UUID.randomUUID();
        LabelResponseDto mockLabel = new LabelResponseDto();
        mockLabel.setId(labelId);
        mockLabel.setName("Sample Label");
        mockLabel.setColor(com.mapzilla.backend.feature.label.enums.Color.YELLOW);

        when(labelService.getLabelById(labelId)).thenReturn(mockLabel);

        mockMvc.perform(get("/labels/{id}", labelId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Successfully fetched label by id" + labelId))
                .andExpect(jsonPath("$.data.id").value(labelId.toString()))
                .andExpect(jsonPath("$.data.name").value("Sample Label"))
                .andExpect(jsonPath("$.data.color").value("YELLOW"));
    }

    @Test
    void getLabelById_ReturnNotFound_WhenLabelDoesNotExist() throws Exception {
        UUID labelId = UUID.randomUUID();

        when(labelService.getLabelById(labelId))
                .thenThrow(new ResourceNotFoundException("Label not found with id: " + labelId));

        mockMvc.perform(get("/labels/{id}", labelId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Label not found with id: " + labelId));
    }

//    ========================Creating Labels===========================


    @Test
    void createLabel_ReturnsCreatedStatus() throws Exception {
        LabelCreateDto requestDto = LabelMockData.createValidLabelCreateDto();
        LabelResponseDto responseDto = LabelMockData.createValidLabelResponseDto();

        when(labelService.createLabel(any(LabelCreateDto.class)))
                .thenReturn(responseDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/labels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk());
    }

    @Test
    void createLabel_ReturnsCreatedStatusAndResponseBody() throws Exception {
        LabelCreateDto requestDto = LabelMockData.createValidLabelCreateDto();
        LabelResponseDto responseDto = LabelMockData.createValidLabelResponseDto();
        when(labelService.createLabel(any(LabelCreateDto.class))).thenReturn(responseDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/labels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Successfully created label"))
                .andExpect(jsonPath("$.data.id").value(responseDto.getId().toString()))
                .andExpect(jsonPath("$.data.name").value(responseDto.getName()))
                .andExpect(jsonPath("$.data.color").value(responseDto.getColor().name()));
    }


    @Test
    void createLabel_ReturnsBadRequest_WhenNameIsEmpty() throws Exception {
        LabelCreateDto requestDto = LabelMockData.createLabelCreateDtoWithEmptyName();

        mockMvc.perform(MockMvcRequestBuilders.post("/labels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createLabel_ReturnsBadRequest_WhenInvalidColor() throws Exception {
        String requestBody = LabelMockData.createInvalidColorJson();

        mockMvc.perform(MockMvcRequestBuilders.post("/labels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        result.getResolvedException().getMessage()
                                .contains("Cannot deserialize value of"));
    }

//    ====================Deleting Labels====================

    @Test
    void deleteLabel_ReturnsSuccess() throws Exception {
        UUID labelId = UUID.randomUUID();

        doNothing().when(labelService).deleteLabel(eq(labelId));

        mockMvc.perform(delete("/labels/{id}", labelId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Successfully deleted label"));
    }

    @Test
    void deleteLabel_LabelNotFound_ReturnsNotFound() throws Exception {
        UUID labelId = UUID.randomUUID();

        doThrow(new ResourceNotFoundException("Label not found with id: " + labelId))
                .when(labelService).deleteLabel(eq(labelId));

        mockMvc.perform(delete("/labels/{id}", labelId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Label not found with id: " + labelId));
    }


//    =======================UPDATING LABELS===========================


    @Test
    void updateLabel_ValidRequest_ReturnsUpdatedLabel() throws Exception {
        UUID labelId = UUID.randomUUID();
        LabelUpdateDto requestDto = LabelMockData.createValidLabelUpdateDto();
        LabelResponseDto responseDto = LabelMockData.createValidLabelResponseDto();

        when(labelService.updateLabel(eq(labelId), any(LabelUpdateDto.class)))
                .thenReturn(responseDto);

        mockMvc.perform(patch("/labels/{id}", labelId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Successfully updated label with id " + labelId))
                .andExpect(jsonPath("$.data.id").value(responseDto.getId().toString()))
                .andExpect(jsonPath("$.data.name").value(responseDto.getName()))
                .andExpect(jsonPath("$.data.color").value(responseDto.getColor().toString()));
    }


    @Test
    void updateLabel_LabelNotFound_ReturnsNotFound() throws Exception {
        UUID labelId = UUID.randomUUID();
        LabelUpdateDto requestDto = LabelMockData.createValidLabelUpdateDto();

        when(labelService.updateLabel(eq(labelId), any(LabelUpdateDto.class)))
                .thenThrow(new ResourceNotFoundException("Label not found with id: " + labelId));

        mockMvc.perform(patch("/labels/{id}", labelId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Label not found with id: " + labelId));
    }

    @Test
    void updateLabel_EmptyName_ReturnsBadRequest() throws Exception {
        UUID labelId = UUID.randomUUID();
        LabelUpdateDto requestDto = LabelMockData.createInvalidLabelUpdateDtoWithEmptyName();

        mockMvc.perform(patch("/labels/{id}", labelId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateLabel_InvalidColorEnum_ReturnsBadRequest() throws Exception {
        UUID labelId = UUID.randomUUID();
        String requestBody = LabelMockData.createInvalidUpdateColorEnumJson();

        mockMvc.perform(patch("/labels/{id}", labelId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.message", containsString("Allowed values")));
    }

}
