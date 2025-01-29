package com.mapzilla.backend.controller;
import com.mapzilla.backend.exceptions.ResourceNotFoundException;
import com.mapzilla.backend.model.FavouriteLocation;
import com.mapzilla.backend.request.FavouriteLocationRequest;
import com.mapzilla.backend.response.ApiResponse;
import com.mapzilla.backend.service.IFavouriteLocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
@RestController
@RequestMapping("/favourite-location")
@RequiredArgsConstructor
@Validated
public class FavouriteLocationControler {
    private final IFavouriteLocationService favouriteLocationService;
    @PostMapping
    public ResponseEntity<ApiResponse> addFavouriteLocation(@Valid @RequestBody FavouriteLocationRequest request){
        try {
            FavouriteLocation favouriteLocation = favouriteLocationService.addFavouriteLocation(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse("Success!", favouriteLocation));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> getFavouriteLocationsByUserId(@PathVariable String userId) {
        List<FavouriteLocation> locations = favouriteLocationService.getFavouriteLocationsByUserId(userId);
        return ResponseEntity.ok(new ApiResponse("Success!", locations));
    }
    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllLocations() {
        List<FavouriteLocation> locations = favouriteLocationService.getAllLocations();
        return ResponseEntity.ok(new ApiResponse("Success!", locations));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteFavouriteLocationById(@PathVariable Long id) {
        try {
            favouriteLocationService.deleteFavouriteLocationById(id);
            return ResponseEntity.status(OK).body(new ApiResponse("Success!", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getFavouriteLocationById(@PathVariable Long id) {
        try {
            FavouriteLocation location = favouriteLocationService.getFavouriteLocationById(id);
            return ResponseEntity.status(OK).body(new ApiResponse("Success!", location));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateFavouriteLocationById(@Valid @RequestBody FavouriteLocationRequest request, @PathVariable Long id) {
        try {
            FavouriteLocation updatedLocation = favouriteLocationService.updateFavouriteLocationById(id, request);
            return ResponseEntity.ok(new ApiResponse("Success!", updatedLocation));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
}
