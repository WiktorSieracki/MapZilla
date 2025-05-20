package com.mapzilla.backend.feature.history.repository;

import com.mapzilla.backend.feature.history.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HistoryRepository extends JpaRepository<History, UUID> {
    History getHistoryById(UUID id);
}
