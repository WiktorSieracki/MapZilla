package com.mapzilla.mapzilla.service.Location;


import com.mapzilla.mapzilla.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LocationService implements ILocationService{
    private final LocationRepository locationRepository;

}
