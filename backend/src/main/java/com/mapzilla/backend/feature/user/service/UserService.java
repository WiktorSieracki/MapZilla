package com.mapzilla.backend.feature.user.service;

import com.mapzilla.backend.feature.user.dto.UserResponseDto;
import com.mapzilla.backend.feature.user.mapper.UserMapper;
import com.mapzilla.backend.feature.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
