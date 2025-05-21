package com.mapzilla.backend.feature.label.service;

import com.mapzilla.backend.feature.label.dto.LabelCreateDto;
import com.mapzilla.backend.feature.label.dto.LabelResponseDto;
import com.mapzilla.backend.feature.label.dto.LabelUpdateDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface LabelService {

    LabelResponseDto getAllLabels();

    LabelResponseDto getLabelById(UUID id);

    LabelResponseDto createLabel(LabelCreateDto labelCreateDto);

    LabelResponseDto updateLabel(UUID id, LabelUpdateDto labelUpdateDto);

    LabelResponseDto deleteLabel(UUID id);

}
