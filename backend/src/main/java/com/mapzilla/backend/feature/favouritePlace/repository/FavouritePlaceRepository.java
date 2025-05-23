package com.mapzilla.backend.feature.favouritePlace.repository;

import com.mapzilla.backend.feature.favouritePlace.model.FavouritePlace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FavouritePlaceRepository extends JpaRepository<FavouritePlace, UUID> {
    Optional<FavouritePlace> findByIdAndUserId(UUID id, String userId);
}
