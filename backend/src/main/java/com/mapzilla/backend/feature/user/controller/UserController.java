package com.mapzilla.backend.feature.user.controller;

import com.mapzilla.backend.feature.user.dto.UserResponseDto;
import com.mapzilla.backend.feature.user.service.UserService;
import com.mapzilla.backend.feature.util.dto.ApiResponse;
import com.mapzilla.backend.feature.util.enums.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping
    public ApiResponse<List<UserResponseDto>> getAllUsersExceptSelf(Authentication authentication) {
        return new ApiResponse<>(
                SuccessCode.RESPONSE_SUCCESSFUL,
                "Successfully fetched all users except yourself",
                userService.finAllUsersExceptSelf(authentication));
    }

    @GetMapping("/all")
    public ApiResponse<List<UserResponseDto>> getAllUsers() {
        return new ApiResponse<>(
                SuccessCode.RESPONSE_SUCCESSFUL,
                "Successfully fetched all users",
                userService.finAllUsers());
    }
}
