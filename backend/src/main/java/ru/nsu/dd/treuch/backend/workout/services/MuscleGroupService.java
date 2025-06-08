package ru.nsu.dd.treuch.backend.workout.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.dd.treuch.backend.workout.dto.MuscleGroupDTO;
import ru.nsu.dd.treuch.backend.workout.models.MuscleGroup;
import ru.nsu.dd.treuch.backend.workout.repositories.MuscleGroupRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MuscleGroupService {
    private final MuscleGroupRepository muscleGroupRepository;

    @Transactional
    public List<MuscleGroupDTO> createMuscleGroups(List<MuscleGroupDTO> dto) {
        var created = dto.stream().map(this::createMuscleGroup).collect(Collectors.toList());
        return created;
    }

    @Transactional
    public MuscleGroupDTO createMuscleGroup(MuscleGroupDTO dto) {
        if (muscleGroupRepository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("Muscle group with " + dto.getName() + " this name already exists");
        }

        MuscleGroup muscleGroup = dto.toEntity();
        muscleGroup = muscleGroupRepository.save(muscleGroup);
        return MuscleGroupDTO.fromEntity(muscleGroup);
    }

    public List<MuscleGroupDTO> getAllMuscleGroups() {
        return muscleGroupRepository.findAll().stream()
                .map(MuscleGroupDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public MuscleGroupDTO getById(Long id) {
        return muscleGroupRepository.findById(id)
                .map(MuscleGroupDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Muscle group not found"));
    }
}