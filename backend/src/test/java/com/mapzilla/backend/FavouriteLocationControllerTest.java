package com.mapzilla.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapzilla.backend.controller.FavouriteLocationControler;
import com.mapzilla.backend.exceptions.ResourceNotFoundException;
import com.mapzilla.backend.favouritePlace.mockData.FavouritePlaceMockData;
import com.mapzilla.backend.model.FavouriteLocation;
import com.mapzilla.backend.request.AddFavouriteLocationRequest;

import com.mapzilla.backend.request.UpdateFavouriteLocationRequest;
import com.mapzilla.backend.service.FavouriteLocationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.mapzilla.backend.favouritePlace.mockData.FavouritePlaceMockData.createMockFavouriteLocation;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = FavouriteLocationControler.class)
@AutoConfigureMockMvc(addFilters = false)
class FavouriteLocationControlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FavouriteLocationService favouriteLocationService;

    @Autowired
    private ObjectMapper objectMapper;
    private AddFavouriteLocationRequest request;

    private AddFavouriteLocationRequest requestBadScore;

    private AddFavouriteLocationRequest requestBadLat;

    private AddFavouriteLocationRequest requestBadLon;

    @BeforeEach
    void setUp() {
        Jwt jwt = Jwt.withTokenValue("fake-token")
                .header("alg", "none")
                .claim("sub", "user123")
                .build();
        Authentication auth = Mockito.mock(Authentication.class);
        when(auth.getPrincipal()).thenReturn(jwt);
        SecurityContextHolder.getContext().setAuthentication(auth);

        requestBadScore = FavouritePlaceMockData.createMockAddFavouriteRequestBadScore();
        request = FavouritePlaceMockData.createMockAddFavouriteRequest();
        requestBadLat = FavouritePlaceMockData.createMockAddFavouriteRequestBadLat();
        requestBadLon = FavouritePlaceMockData.createMockAddFavouriteRequestBadLon();
    }


    //===================AddFavouriteLocation==================
    @Test
    public void CreateLocation_ReturnCreated() throws Exception {
        FavouriteLocation favouriteLocation = createMockFavouriteLocation(request);
        when(favouriteLocationService.addFavouriteLocation(request)).thenReturn(favouriteLocation);
        mockMvc.perform(post("/favourite-location")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    public void CreateLocation_ReturnCreated_CheckResponse() throws Exception {
        AddFavouriteLocationRequest request = FavouritePlaceMockData.createMockAddFavouriteRequest();
        FavouriteLocation favouriteLocation = FavouritePlaceMockData.createMockFavouriteLocation(request);

        when(favouriteLocationService.addFavouriteLocation(any(AddFavouriteLocationRequest.class))).thenReturn(favouriteLocation);
        mockMvc.perform(post("/favourite-location")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.score").value(10));
    }

    @Test
    public void CreateLocation_ReturnBadRequest_Score() throws Exception {
        FavouriteLocation favouriteLocation = createMockFavouriteLocation(requestBadScore);
        when(favouriteLocationService.addFavouriteLocation(requestBadScore)).thenReturn(favouriteLocation);
        mockMvc.perform(post("/favourite-location")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBadScore)))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void CreateLocation_ReturnBadRequest_Lat() throws Exception {
        FavouriteLocation favouriteLocation = createMockFavouriteLocation(requestBadLat);
        when(favouriteLocationService.addFavouriteLocation(requestBadLat)).thenReturn(favouriteLocation);
        mockMvc.perform(post("/favourite-location")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBadLat)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void CreateLocation_ReturnBadRequest_Lon() throws Exception {
        FavouriteLocation favouriteLocation = createMockFavouriteLocation(requestBadLat);
        when(favouriteLocationService.addFavouriteLocation(requestBadLat)).thenReturn(favouriteLocation);
        mockMvc.perform(post("/favourite-location")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBadLat)))
                .andExpect(status().isBadRequest());
    }




    //===================GetFavouriteLocationsForUser==================

    @Test
    public void GetFavouriteLocationsByUserId_ReturnSuccess() throws Exception {
        String userId = "user123";
        AddFavouriteLocationRequest request = FavouritePlaceMockData.createMockAddFavouriteRequest();
        FavouriteLocation favouriteLocation = FavouritePlaceMockData.createMockFavouriteLocation(request);

        List<FavouriteLocation> locations = List.of(favouriteLocation, favouriteLocation);

        when(favouriteLocationService.getFavouriteLocationsByUserId(userId)).thenReturn(locations);

        mockMvc.perform(get("/favourite-location/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Success!"))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data[0].score").value(10.00))
                .andExpect(jsonPath("$.data[1].score").value(10.00));
    }

    @Test
    public void GetFavouriteLocationsByUserId_ReturnSuccess_Empty() throws Exception {
        String userId = "user123";
        List<FavouriteLocation> locations = List.of();
        when(favouriteLocationService.getFavouriteLocationsByUserId(userId)).thenReturn(locations);

        mockMvc.perform(get("/favourite-location/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Success!"))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(0));

    }

//========================DeleteFavouriteLocation=================


    @Test
    public void DeleteFavouriteLocationById_ReturnSuccess() throws Exception {
        Long locationId = 1L;
        doNothing().when(favouriteLocationService).deleteFavouriteLocationById(locationId);
        mockMvc.perform(delete("/favourite-location/{id}", locationId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Success!"));
    }

    @Test
    public void DeleteFavouriteLocationById_ReturnNotFound() throws Exception {
        Long locationId = 1L;

        doThrow(new ResourceNotFoundException("Location not found")).when(favouriteLocationService).deleteFavouriteLocationById(locationId);

        mockMvc.perform(delete("/favourite-location/{id}", locationId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Location not found"));
    }


    //===================GetFavouriteLocationById======================
    @Test
    public void GetFavouriteLocationById_ReturnSuccess() throws Exception {
        Long locationId = 1L;
        AddFavouriteLocationRequest request = FavouritePlaceMockData.createMockAddFavouriteRequest();
        FavouriteLocation location = FavouritePlaceMockData.createMockFavouriteLocation(request);

        when(favouriteLocationService.getFavouriteLocationById(locationId)).thenReturn(location);

        mockMvc.perform(get("/favourite-location/{id}", locationId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Success!"))
                .andExpect(jsonPath("$.data.id").value(location.getId()))
                .andExpect(jsonPath("$.data.score").value(location.getScore()));
    }

    @Test
    public void GetFavouriteLocationById_ReturnNotFound() throws Exception {
        Long locationId = 999L;

        when(favouriteLocationService.getFavouriteLocationById(locationId))
                .thenThrow(new ResourceNotFoundException("Location not found"));

        mockMvc.perform(get("/favourite-location/{id}", locationId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Location not found"));
    }


    //===================UpdateFavouriteLocationById===============================


    @Test
    public void UpdateFavouriteLocationById_ReturnSuccess() throws Exception {
        Long id = 1L;
        UpdateFavouriteLocationRequest updateRequest = FavouritePlaceMockData.createMockUpdateRequest();
        FavouriteLocation updatedLocation = FavouritePlaceMockData.createMockUpdatedFavouriteLocation(updateRequest);

        when(favouriteLocationService.updateFavouriteLocationById(eq(id), any(UpdateFavouriteLocationRequest.class)))
                .thenReturn(updatedLocation);

        mockMvc.perform(put("/favourite-location/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Success!"))
                .andExpect(jsonPath("$.data.score").value(updateRequest.getScore()));
    }

    @Test
    public void UpdateFavouriteLocationById_ReturnNotFound() throws Exception {
        Long id = 999L;
        UpdateFavouriteLocationRequest updateRequest = FavouritePlaceMockData.createMockUpdateRequest();

        when(favouriteLocationService.updateFavouriteLocationById(eq(id), any(UpdateFavouriteLocationRequest.class)))
                .thenThrow(new ResourceNotFoundException("Location not found"));

        mockMvc.perform(put("/favourite-location/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void UpdateFavouriteLocationById_BadRequest_Score() throws Exception {
        Long id = 1L;
        UpdateFavouriteLocationRequest badRequest = FavouritePlaceMockData.createMockUpdateRequestBadScore();

        mockMvc.perform(put("/favourite-location/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(badRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void UpdateFavouriteLocationById_BadRequest_Lat() throws Exception {
        Long id = 1L;
        UpdateFavouriteLocationRequest badRequest = FavouritePlaceMockData.createMockUpdateRequestBadLat();

        mockMvc.perform(put("/favourite-location/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(badRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void UpdateFavouriteLocationById_BadRequest_Lon() throws Exception {
        Long id = 1L;
        UpdateFavouriteLocationRequest badRequest = FavouritePlaceMockData.createMockUpdateRequestBadLon();

        mockMvc.perform(put("/favourite-location/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(badRequest)))
                .andExpect(status().isBadRequest());
    }

}



