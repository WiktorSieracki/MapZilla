package com.mapzilla.backend.repository;

import com.mapzilla.backend.model.FavouriteLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavouriteLocationRepository extends JpaRepository<FavouriteLocation, Long> {
    List<FavouriteLocation> findByUserId(String userId);
}