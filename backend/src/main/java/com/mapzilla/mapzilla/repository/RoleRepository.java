package com.mapzilla.mapzilla.repository;

import com.mapzilla.mapzilla.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}