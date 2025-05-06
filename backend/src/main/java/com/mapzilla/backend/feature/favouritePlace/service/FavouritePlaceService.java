package com.mapzilla.backend.feature.favouritePlace.service;

import com.mapzilla.backend.feature.favouritePlace.model.FavouritePlace;
import com.mapzilla.backend.model.FavouriteLocation;
import com.mapzilla.backend.request.AddFavouriteLocationRequest;
import com.mapzilla.backend.request.UpdateFavouriteLocationRequest;

import java.util.List;

public interface FavouritePlaceService {

    FavouritePlace addFavouriteLocation(AddFavouriteLocationRequest request);
    List<FavouritePlace> getFavouriteLocationsByUserId(String userId);
    List<FavouritePlace> getAllLocations();
    FavouritePlace getFavouriteLocationById(Long id);
    void deleteFavouriteLocationById(Long id);
    FavouritePlace updateFavouriteLocationById(Long id, UpdateFavouriteLocationRequest request);
}
