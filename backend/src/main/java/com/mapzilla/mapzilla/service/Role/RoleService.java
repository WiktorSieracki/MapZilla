package com.mapzilla.mapzilla.service.Role;

import com.mapzilla.mapzilla.repository.RoleRepository;
import com.mapzilla.mapzilla.service.User.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;

}
