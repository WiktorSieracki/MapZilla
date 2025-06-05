package com.mapzilla.backend.feature.favouritePlace.controller;

import com.mapzilla.backend.feature.favouritePlace.dto.FavouritePlaceCreateDto;
import com.mapzilla.backend.feature.favouritePlace.dto.FavouritePlaceResponseDto;
import com.mapzilla.backend.feature.favouritePlace.dto.FavouritePlaceUpdateDto;
import com.mapzilla.backend.feature.favouritePlace.dto.FavouritePlaceUpdateRequestDto;
import com.mapzilla.backend.feature.favouritePlace.service.FavouritePlaceService;
import com.mapzilla.backend.feature.util.dto.ApiResponse;
import com.mapzilla.backend.feature.util.enums.SuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fav-place")
@RequiredArgsConstructor
public class FavouritePlaceController {
    private final FavouritePlaceService favouritePlaceService;

    @PostMapping
    public ApiResponse<FavouritePlaceResponseDto> addFavouritePlace(@AuthenticationPrincipal Jwt jwt, @RequestBody @Valid FavouritePlaceCreateDto favouritePlaceCreateDto){
        return new ApiResponse<>(
                SuccessCode.RESOURCE_CREATED,
                "Successfully added place to list of favourites",
                favouritePlaceService.addFavouritePlace(jwt, favouritePlaceCreateDto)
        );
    }

    @GetMapping()
    public ApiResponse<List<FavouritePlaceResponseDto>> getAllLocations(@AuthenticationPrincipal Jwt jwt) {
        return new ApiResponse<>(
                SuccessCode.RESPONSE_SUCCESSFUL,
                "Successfully fetched favourite places of a user",
                favouritePlaceService.getAllFavouritePlaces(jwt)
        );
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deleteFavouriteLocationById(@AuthenticationPrincipal Jwt jwt, @PathVariable UUID id) {
        favouritePlaceService.deleteFavouriteLocationById(jwt, id);
        return new ApiResponse<>(
                SuccessCode.RESOURCE_DELETED,
                "Successfully deleted favourite place from list",
                null
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<FavouritePlaceResponseDto> getFavouritePlaceById(@PathVariable UUID id, @AuthenticationPrincipal Jwt jwt) {
        return new ApiResponse<>(
                SuccessCode.RESPONSE_SUCCESSFUL,
                "Successfully fetched favourite place with id " + id,
                favouritePlaceService.getFavouritePlaceById(jwt, id)
        );
    }

    @PatchMapping("/{id}")
    public ApiResponse<FavouritePlaceResponseDto> updateFavouriteLocationById(@PathVariable UUID id, @AuthenticationPrincipal Jwt jwt, @RequestBody @Valid FavouritePlaceUpdateRequestDto favouritePlaceUpdateRequestDto) {
        return new ApiResponse<>(
                SuccessCode.RESOURCE_UPDATED,
                "Successfully updated favourite place with id " + id,
                favouritePlaceService.updateFavouritePlaceById(jwt, id, favouritePlaceUpdateRequestDto)
        );
    }
}
