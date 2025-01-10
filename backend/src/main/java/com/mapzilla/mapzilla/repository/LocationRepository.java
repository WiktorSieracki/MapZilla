package com.mapzilla.mapzilla.repository;


import com.mapzilla.mapzilla.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {

}