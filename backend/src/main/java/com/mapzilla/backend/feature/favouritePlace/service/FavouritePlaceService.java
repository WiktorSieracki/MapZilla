package com.mapzilla.backend.feature.favouritePlace.service;

import com.mapzilla.backend.feature.favouritePlace.model.FavouritePlace;
import com.mapzilla.backend.request.AddFavouriteLocationRequest;
import com.mapzilla.backend.request.UpdateFavouriteLocationRequest;

import java.util.List;
import java.util.UUID;

public interface FavouritePlaceService {

    FavouritePlace addFavouritePlace(AddFavouriteLocationRequest request);

    List<FavouritePlace> getFavouritePlacesByUserId(String userId);

    List<FavouritePlace> getAllPlaces();

    FavouritePlace getFavouritePlaceById(UUID id);

    void deleteFavouritePlaceById(UUID id);

    FavouritePlace updateFavouritePlaceById(UUID id, UpdateFavouriteLocationRequest request);
}
