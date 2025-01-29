package com.mapzilla.backend.service;

import com.mapzilla.backend.exceptions.ResourceNotFoundException;
import com.mapzilla.backend.model.FavouriteLocation;
import com.mapzilla.backend.repository.FavouriteLocationRepository;
import com.mapzilla.backend.request.FavouriteLocationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavouriteLocationService implements IFavouriteLocationService{

    private final FavouriteLocationRepository favouriteLocationRepository;
    @Override
    public FavouriteLocation addFavouriteLocation(FavouriteLocationRequest request) {
        FavouriteLocation location = new FavouriteLocation();
        location.setUserId(request.getUserId());
        location.setScore(request.getScore());
        location.setLat(request.getLat());
        location.setLon(request.getLon());
        location.setAvailablePlaces(request.getAvailablePlaces());
        location.setNotAvailablePlaces(request.getNotAvailablePlaces());
        return favouriteLocationRepository.save(location);
    }
    @Override
    public List<FavouriteLocation> getFavouriteLocationsByUserId(String userId) {
        return favouriteLocationRepository.findByUserId(userId);
    }

    @Override
    public List<FavouriteLocation> getAllLocations() {
        return favouriteLocationRepository.findAll();
    }
    @Override
    public FavouriteLocation getFavouriteLocationById(Long id) {
        return favouriteLocationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Favourite location not found with id: " + id));
    }
    @Override
    public void deleteFavouriteLocationById(Long id) {
        FavouriteLocation location = favouriteLocationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Favourite location not found with id: " + id));
        favouriteLocationRepository.delete(location);
    }
    @Override
    public FavouriteLocation updateFavouriteLocationById(Long id, FavouriteLocationRequest request) {
        FavouriteLocation location = favouriteLocationRepository.findById(id)
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
        return favouriteLocationRepository.save(location);
    }

}
