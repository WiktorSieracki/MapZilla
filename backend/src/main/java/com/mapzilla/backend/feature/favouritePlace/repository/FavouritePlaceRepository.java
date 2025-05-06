package com.mapzilla.backend.feature.favouritePlace.repository;

import com.mapzilla.backend.feature.favouritePlace.model.FavouritePlace;
import com.mapzilla.backend.model.FavouriteLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavouritePlaceRepository extends JpaRepository<FavouritePlace, Long> {
    List<FavouritePlace> findByUserId(String userId);
}
