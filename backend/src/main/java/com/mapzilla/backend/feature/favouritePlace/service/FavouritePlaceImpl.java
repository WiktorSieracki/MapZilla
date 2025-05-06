package com.mapzilla.backend.feature.favouritePlace.service;

import com.mapzilla.backend.exceptions.ResourceNotFoundException;
import com.mapzilla.backend.feature.favouritePlace.model.FavouritePlace;
import com.mapzilla.backend.feature.favouritePlace.repository.FavouritePlaceRepository;
import com.mapzilla.backend.model.FavouriteLocation;
import com.mapzilla.backend.request.AddFavouriteLocationRequest;
import com.mapzilla.backend.request.UpdateFavouriteLocationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavouritePlaceImpl implements FavouritePlaceService {
    private final FavouritePlaceRepository favouritePlaceRepository;

    @Override
    public FavouritePlace addFavouriteLocation(AddFavouriteLocationRequest request) {

        FavouritePlace location = new FavouritePlace();
        location.setUserId(request.getUserId());
        location.setScore(request.getScore());
        location.setLat(request.getLat());
        location.setLon(request.getLon());
        location.setAvailablePlaces(request.getAvailablePlaces());
        location.setNotAvailablePlaces(request.getNotAvailablePlaces());
        return favouritePlaceRepository.save(location);
    }
    @Override
    public List<FavouritePlace> getFavouriteLocationsByUserId(String userId) {
        return favouritePlaceRepository.findByUserId(userId);
    }

    @Override
    public List<FavouritePlace> getAllLocations() {
        return favouritePlaceRepository.findAll();
    }
    @Override
    public FavouritePlace getFavouriteLocationById(Long id) {
        return favouritePlaceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Favourite location not found with id: " + id));
    }
    @Override
    public void deleteFavouriteLocationById(Long id) {
        FavouritePlace location = favouritePlaceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Favourite location not found with id: " + id));
        favouritePlaceRepository.delete(location);
    }
    @Override
    public FavouritePlace updateFavouriteLocationById(Long id, UpdateFavouriteLocationRequest request) {
        FavouritePlace location = favouritePlaceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Favourite location not found with id: " + id));

        if (request.getScore() != null) {
            location.setScore(request.getScore());
        }
        if (request.getLat() != null) {
            location.setLat(request.getLat());
        }
        if (request.getLon() != null) {
            location.setLon(request.getLon());
        }
        if (request.getAvailablePlaces() != null) {
            location.setAvailablePlaces(request.getAvailablePlaces());
        }
        if (request.getNotAvailablePlaces() != null) {
            location.setNotAvailablePlaces(request.getNotAvailablePlaces());
        }
        return favouritePlaceRepository.save(location);
    }

}
