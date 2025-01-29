package com.mapzilla.backend.service;

import com.mapzilla.backend.model.FavouriteLocation;
import com.mapzilla.backend.request.FavouriteLocationRequest;

import java.util.List;

public interface IFavouriteLocationService {
    FavouriteLocation addFavouriteLocation(FavouriteLocationRequest request);
    List<FavouriteLocation> getFavouriteLocationsByUserId(String userId);
    List<FavouriteLocation> getAllLocations();
    FavouriteLocation getFavouriteLocationById(Long id);
    void deleteFavouriteLocationById(Long id);
    FavouriteLocation updateFavouriteLocationById(Long id, FavouriteLocationRequest request);
}
