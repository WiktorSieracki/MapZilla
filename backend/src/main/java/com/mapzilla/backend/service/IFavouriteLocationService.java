package com.mapzilla.backend.service;

import com.mapzilla.backend.model.FavouriteLocation;
import com.mapzilla.backend.request.AddFavouriteLocationRequest;
import com.mapzilla.backend.request.UpdateFavouriteLocationRequest;

import java.util.List;

public interface IFavouriteLocationService {
    FavouriteLocation addFavouriteLocation(AddFavouriteLocationRequest request);
    List<FavouriteLocation> getFavouriteLocationsByUserId(String userId);
    List<FavouriteLocation> getAllLocations();
    FavouriteLocation getFavouriteLocationById(Long id);
    void deleteFavouriteLocationById(Long id);
    FavouriteLocation updateFavouriteLocationById(Long id, UpdateFavouriteLocationRequest request);
}
