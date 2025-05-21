package com.mapzilla.backend.feature.label.service;

import com.mapzilla.backend.feature.label.dto.LabelCreateDto;
import com.mapzilla.backend.feature.label.dto.LabelResponseDto;
import com.mapzilla.backend.feature.label.dto.LabelUpdateDto;
import com.mapzilla.backend.feature.label.repository.LabelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LabelServiceImpl implements LabelService {
    private final LabelRepository labelRepository;

    @Override
    public LabelResponseDto getAllLabels() {
        return null;
    }

    @Override
    public LabelResponseDto getLabelById(UUID id) {
        return null;
    }

    @Override
    public LabelResponseDto createLabel(LabelCreateDto labelCreateDto) {
        return null;
    }

    @Override
    public LabelResponseDto updateLabel(UUID id, LabelUpdateDto labelUpdateDto) {
        return null;
    }

    @Override
    public LabelResponseDto deleteLabel(UUID id) {
        return null;
    }
}
