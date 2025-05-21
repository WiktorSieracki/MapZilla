package com.mapzilla.backend.feature.favouritePlace.repository;

import com.mapzilla.backend.feature.favouritePlace.model.FavouritePlace;
import com.mapzilla.backend.model.FavouriteLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FavouritePlaceRepository extends JpaRepository<FavouritePlace, UUID> {
    List<FavouritePlace> findByUserId(String userId);
}
