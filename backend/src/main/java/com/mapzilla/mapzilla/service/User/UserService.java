package com.mapzilla.mapzilla.service.User;

import com.mapzilla.mapzilla.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements IUserService{
    private final UserRepository userRepository;

}
