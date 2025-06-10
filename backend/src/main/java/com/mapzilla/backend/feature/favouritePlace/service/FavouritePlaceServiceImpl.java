package com.mapzilla.backend.feature.favouritePlace.service;

import com.mapzilla.backend.exceptions.ResourceNotFoundException;
import com.mapzilla.backend.feature.favouritePlace.dto.FavouritePlaceCreateDto;
import com.mapzilla.backend.feature.favouritePlace.dto.FavouritePlaceResponseDto;
import com.mapzilla.backend.feature.favouritePlace.dto.FavouritePlaceUpdateDto;
import com.mapzilla.backend.feature.favouritePlace.dto.FavouritePlaceUpdateRequestDto;
import com.mapzilla.backend.feature.favouritePlace.model.FavouritePlace;
import com.mapzilla.backend.feature.favouritePlace.repository.FavouritePlaceRepository;
import com.mapzilla.backend.feature.label.model.Label;
import com.mapzilla.backend.feature.label.service.LabelServiceImpl;
import com.mapzilla.backend.feature.user.model.User;
import com.mapzilla.backend.feature.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavouritePlaceServiceImpl implements FavouritePlaceService {
    private final FavouritePlaceRepository favouritePlaceRepository;
    private final UserService userService;
    private final LabelServiceImpl labelService;

    @Override
    @Transactional
    public FavouritePlaceResponseDto addFavouritePlace(Jwt jwt, FavouritePlaceCreateDto favouritePlaceCreateDto) {
        User user = userService.getUser(jwt);

        FavouritePlace favPlace = new FavouritePlace();
        favPlace.setUser(user);
        favPlace.setScore(favouritePlaceCreateDto.getScore());
        favPlace.setLat(favouritePlaceCreateDto.getLat());
        favPlace.setLon(favouritePlaceCreateDto.getLon());
        favPlace.setAvailablePlaces(favouritePlaceCreateDto.getAvailablePlaces());
        favPlace.setNotAvailablePlaces(favouritePlaceCreateDto.getNotAvailablePlaces());

        user.getFavouritePlaces().add(favPlace);

        favouritePlaceRepository.save(favPlace);

        return FavouritePlaceResponseDto.from(favPlace);
    }

    @Override
    public List<FavouritePlaceResponseDto> getAllFavouritePlaces(Jwt jwt) {
        return favouritePlaceRepository.findAll().stream().map(FavouritePlaceResponseDto::from).toList();
    }

    @Override
    public FavouritePlaceResponseDto getFavouritePlaceById(Jwt jwt, UUID id) {
        User user = userService.getUser(jwt);

        FavouritePlace favouritePlace = favouritePlaceRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Favourite location not found with id: " + id));

        return FavouritePlaceResponseDto.from(favouritePlace);
    }

    @Override
    @Transactional
    public void deleteFavouriteLocationById(Jwt jwt, UUID id) {
        FavouritePlace location = favouritePlaceRepository.findById(id)
                //TODO - change error handling
                .orElseThrow(() -> new ResourceNotFoundException("Favourite location not found with id: " + id));
        favouritePlaceRepository.delete(location);
    }

    @Override
    @Transactional
    public FavouritePlaceResponseDto updateFavouritePlaceById(Jwt jwt, UUID id, FavouritePlaceUpdateRequestDto favouritePlaceUpdateRequestDto) {
        FavouritePlace favouritePlace = favouritePlaceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Favourite location not found with id: " + id));

        Set<Label> newLabels = favouritePlaceUpdateRequestDto
                .getLabels()
                .map(labelService::getLabelsByIdOrThrow)
                .orElseGet(HashSet::new);

        FavouritePlaceUpdateDto favouritePlaceUpdateDto = FavouritePlaceUpdateDto.from(favouritePlaceUpdateRequestDto, newLabels);

        favouritePlace.update(favouritePlaceUpdateDto);
        favouritePlaceRepository.save(favouritePlace);

        return FavouritePlaceResponseDto.from(favouritePlace);
    }

}
