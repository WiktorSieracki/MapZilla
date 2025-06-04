package com.mapzilla.backend.feature.label.service;

import com.mapzilla.backend.exceptions.ResourceNotFoundException;
import com.mapzilla.backend.feature.label.dto.LabelCreateDto;
import com.mapzilla.backend.feature.label.dto.LabelResponseDto;
import com.mapzilla.backend.feature.label.dto.LabelUpdateDto;
import com.mapzilla.backend.feature.label.model.Label;
import com.mapzilla.backend.feature.label.repository.LabelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LabelServiceImpl implements LabelService {
    private final LabelRepository labelRepository;

    @Override
    public Set<LabelResponseDto> getAllLabels() {
        return labelRepository.findAll().stream().map(LabelResponseDto::from).collect(Collectors.toSet());
    }

    @Override
    public LabelResponseDto getLabelById(UUID id) {
        Label label = labelRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Label not found with id: " + id)
        );

        return LabelResponseDto.from(label);
    }

    @Override
    @Transactional
    public LabelResponseDto createLabel(LabelCreateDto labelCreateDto) {
        Label label = new Label();
        label.setName(labelCreateDto.getName());
        label.setColor(labelCreateDto.getColor());

        return LabelResponseDto.from(labelRepository.save(label));
    }

    @Override
    @Transactional
    public LabelResponseDto updateLabel(UUID id, LabelUpdateDto labelUpdateDto) {
        Label label = labelRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Label not found with id: " + id)
        );

        if (labelUpdateDto.getName() != null) {
            label.setName(labelUpdateDto.getName());
        }
        if (labelUpdateDto.getColor() != null) {
            label.setColor(labelUpdateDto.getColor());
        }

        return LabelResponseDto.from(labelRepository.save(label));
    }

    @Override
    @Transactional
    public void deleteLabel(UUID id) {
        if (!labelRepository.existsById(id)) {
            throw new ResourceNotFoundException("Label not found with id: " + id);
        }

        labelRepository.deleteById(id);
    }

    public Set<Label> getLabelsByIdOrThrow(Set<UUID> ids) {
       Set<Label> labels = labelRepository.findAllByIdIn(ids);

       Set<UUID> foundLabels = labels.stream().map(Label::getId).collect(Collectors.toSet());

       ids.removeAll(foundLabels);

       if (!ids.isEmpty()) {
           throw new RuntimeException("Labels not found with ids: " + ids);
       }

       return labels;
    }
}
