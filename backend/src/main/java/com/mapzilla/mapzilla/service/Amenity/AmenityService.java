package com.mapzilla.mapzilla.service.Amenity;

import com.mapzilla.mapzilla.repository.AmenityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AmenityService implements IAmenityService{
    private final AmenityRepository amenityRepository;

}
