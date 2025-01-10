package com.mapzilla.mapzilla.repository;

import com.mapzilla.mapzilla.model.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {
}