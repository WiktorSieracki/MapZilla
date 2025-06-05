package com.mapzilla.backend.map.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapzilla.backend.feature.map.controller.MapController;
import com.mapzilla.backend.feature.map.dto.MapResponseDto;
import com.mapzilla.backend.feature.map.dto.OverpassApiRequest;
import com.mapzilla.backend.feature.map.service.MapService;
import com.mapzilla.backend.feature.user.service.UserSynchronizer;
import com.mapzilla.backend.feature.util.enums.SuccessCode;
import org.junit.jupiter.api.Test;
import com.mapzilla.backend.map.mockData.MapMockData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MapController.class)
@AutoConfigureMockMvc(addFilters = false)
class MapControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MapService mapService;

    @MockitoBean
    private UserSynchronizer userSynchronizer;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getMapData_ValidRequest_ReturnsOk() throws Exception {
        OverpassApiRequest request = MapMockData.createValidRequest();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/map/locate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Successfully fetched information"));
    }


    @Test
    void getMapData_InvalidLatitude_ReturnsBadRequest() throws Exception {
        String invalidLatJson = MapMockData.createInvalidLatitudeJson();

        mockMvc.perform(post("/map/locate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidLatJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getMapData_InvalidLongitude_ReturnsBadRequest() throws Exception {
        String invalidLonJson = MapMockData.createInvalidLongitudeJson();

        mockMvc.perform(post("/map/locate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidLonJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getMapData_MissingTypes_ReturnsBadRequest() throws Exception {
        String missingTypesJson = MapMockData.createMissingTypesJson();

        mockMvc.perform(post("/map/locate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(missingTypesJson))
                .andExpect(status().isBadRequest());

    }

}
