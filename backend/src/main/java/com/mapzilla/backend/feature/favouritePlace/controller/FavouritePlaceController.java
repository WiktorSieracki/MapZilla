package com.mapzilla.backend.feature.favouritePlace.controller;

import com.mapzilla.backend.exceptions.ResourceNotFoundException;
import com.mapzilla.backend.feature.favouritePlace.dto.FavouritePlaceResponseDto;
import com.mapzilla.backend.feature.favouritePlace.service.FavouritePlaceService;
import com.mapzilla.backend.feature.util.dto.ApiResponse;
import com.mapzilla.backend.feature.util.enums.SuccessCode;
import com.mapzilla.backend.model.FavouriteLocation;
import com.mapzilla.backend.request.AddFavouriteLocationRequest;
import com.mapzilla.backend.request.UpdateFavouriteLocationRequest;
import com.mapzilla.backend.service.IFavouriteLocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/fav-place")
@RequiredArgsConstructor
public class FavouritePlaceController {
    private final IFavouriteLocationService favouriteLocationService;
    private final FavouritePlaceService favouritePlaceService;

    @PostMapping
    public ResponseEntity<ApiResponse> addFavouriteLocation(@RequestBody @Valid AddFavouriteLocationRequest request){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwt = (Jwt) authentication.getPrincipal();
            String userId = jwt.getClaim("sub");
            request.setUserId(userId);
            FavouriteLocation favouriteLocation = favouriteLocationService.addFavouriteLocation(request);
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
            List<FavouriteLocation> locations = favouriteLocationService.getFavouriteLocationsByUserId(userId);
            return ResponseEntity.ok(new ApiResponse("Success!", locations));
        }catch(JwtException e){

            return ResponseEntity.status(UNAUTHORIZED).body(new ApiResponse(e.getMessage(), null));

        }
    }

    @GetMapping()
    public ApiResponse<FavouritePlaceResponseDto> getAllLocations(@AuthenticationPrincipal Jwt jwt) {
        return new ApiResponse<>(
                SuccessCode.RESPONSE_SUCCESSFUL,
                "Successfully fetched favourite places of a user",
                favouritePlaceService.getAllFavouritePlaces(jwt)
        );
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deleteFavouriteLocationById(@AuthenticationPrincipal Jwt jwt, @PathVariable UUID id) {
        return new ApiResponse<>(
                SuccessCode.RESOURCE_DELETED,
                "Successfully deleted favourite place from list",
                favouritePlaceService.deleteFavouriteLocationById(jwt, id)
        );
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
    public ResponseEntity<ApiResponse> updateFavouriteLocationById(@RequestBody @Valid UpdateFavouriteLocationRequest request, @PathVariable Long id) {
        try {
            FavouriteLocation updatedLocation = favouriteLocationService.updateFavouriteLocationById(id, request);
            return ResponseEntity.ok(new ApiResponse("Success!", updatedLocation));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
}
