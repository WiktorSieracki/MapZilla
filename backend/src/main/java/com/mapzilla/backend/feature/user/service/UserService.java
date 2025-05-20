package com.mapzilla.backend.feature.user.service;

import com.mapzilla.backend.feature.user.dto.UserResponseDto;
import com.mapzilla.backend.feature.user.mapper.UserMapper;
import com.mapzilla.backend.feature.user.model.User;
import com.mapzilla.backend.feature.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//import java.util.stream.Stream;
//import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponseDto> finAllUsersExceptSelf(Authentication connectedUser) {
        return userRepository.findAllUsersExceptSelf(connectedUser.getName())
                .stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    public List<UserResponseDto> finAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    public User getUser(Jwt jwt) {
        return userRepository.findByEmail(jwt.getClaim("email").toString())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

//    public User getUserDiagnoze(Jwt jwt) {
//        Optional<User> user = userRepository.findById(userId);
//        User userWithHistory = user.orElseThrow();
//        userWithHistory.getHistory();  // powinno być nie-null, jeśli powiązanie istnieje
//    }
}
