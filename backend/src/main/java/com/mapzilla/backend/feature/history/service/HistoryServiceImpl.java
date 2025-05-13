package com.mapzilla.backend.feature.history.service;

import com.mapzilla.backend.feature.history.model.History;
import com.mapzilla.backend.feature.history.repository.HistoryRepository;
import com.mapzilla.backend.feature.history.utils.Location;
import com.mapzilla.backend.feature.user.model.User;
import com.mapzilla.backend.feature.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;
    private final UserService userService;

    @Override
    @Transactional
    public void addToHistory(Jwt jwt, Location location) {
        User user = userService.getUser(jwt);

        user.getHistory().getPlaces().add(location);
    }

    @Override
    public History getUserHistory(Jwt jwt) {
        User user = userService.getUser(jwt);

        return historyRepository.getHistoryById(user.getHistory().getId());
    }
}
