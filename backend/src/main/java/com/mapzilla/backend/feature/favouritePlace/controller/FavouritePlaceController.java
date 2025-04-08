package com.mapzilla.backend.feature.favouritePlace.controller;

import com.mapzilla.backend.exceptions.ResourceNotFoundException;
import com.mapzilla.backend.feature.favouritePlace.model.FavouritePlace;
import com.mapzilla.backend.feature.favouritePlace.service.FavouritePlaceService;
import com.mapzilla.backend.request.AddFavouriteLocationRequest;
import com.mapzilla.backend.request.UpdateFavouriteLocationRequest;
import com.mapzilla.backend.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/favourite-place")
@RequiredArgsConstructor
public class FavouritePlaceController {

    private final FavouritePlaceService favouritePlaceService;

    @PostMapping
    public ResponseEntity<ApiResponse> addFavouritePlace(@RequestBody @Valid AddFavouriteLocationRequest request){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwt = (Jwt) authentication.getPrincipal();
            String userId = jwt.getClaim("sub");
            request.setUserId(userId);
            FavouritePlace favouriteLocation = favouritePlaceService.addFavouritePlace(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse("Success!", favouriteLocation));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }catch (JwtException e){
            return ResponseEntity.status(UNAUTHORIZED).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/user")
    public ResponseEntity<ApiResponse> getFavouriteLocationsByUserId() {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwt = (Jwt) authentication.getPrincipal();
            String userId = jwt.getClaim("sub");
            List<FavouritePlace> locations = favouritePlaceService.getFavouritePlacesByUserId(userId);
            return ResponseEntity.ok(new ApiResponse("Success!", locations));
        }catch(JwtException e){

            return ResponseEntity.status(UNAUTHORIZED).body(new ApiResponse(e.getMessage(), null));

        }
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllLocations() {
        List<FavouritePlace> locations = favouritePlaceService.getAllPlaces();
        return ResponseEntity.ok(new ApiResponse("Success!", locations));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteFavouriteLocationById(@PathVariable UUID id) {
        try {
            favouritePlaceService.deleteFavouritePlaceById(id);
            return ResponseEntity.status(OK).body(new ApiResponse("Success!", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getFavouriteLocationById(@PathVariable UUID id) {
        try {
            FavouritePlace location = favouritePlaceService.getFavouritePlaceById(id);
            return ResponseEntity.status(OK).body(new ApiResponse("Success!", location));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateFavouriteLocationById(@RequestBody @Valid UpdateFavouriteLocationRequest request, @PathVariable UUID id) {
        try {
            FavouritePlace updatedLocation = favouritePlaceService.updateFavouritePlaceById(id, request);
            return ResponseEntity.ok(new ApiResponse("Success!", updatedLocation));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
}
