package com.mapzilla.backend.feature.label.repository;

import com.mapzilla.backend.feature.label.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LabelRepository extends JpaRepository<Label, UUID> {

    Optional<Label> getLabelById(UUID id);
}
