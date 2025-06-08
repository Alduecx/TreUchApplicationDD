package ru.nsu.dd.treuch.backend.workout.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.dd.treuch.backend.security.model.User;
import ru.nsu.dd.treuch.backend.security.repositories.UserRepository;
import ru.nsu.dd.treuch.backend.workout.dto.CreateExerciseTemplateDTO;
import ru.nsu.dd.treuch.backend.workout.dto.CreateExerciseTemplateMediaDTO;
import ru.nsu.dd.treuch.backend.workout.dto.ExerciseTemplateDTO;
import ru.nsu.dd.treuch.backend.workout.dto.ExerciseTemplateMediaDTO;
import ru.nsu.dd.treuch.backend.workout.models.ExerciseTemplate;
import ru.nsu.dd.treuch.backend.workout.models.MuscleGroup;
import ru.nsu.dd.treuch.backend.workout.repositories.ExerciseRepository;
import ru.nsu.dd.treuch.backend.workout.repositories.ExerciseTemplateRepository;
import ru.nsu.dd.treuch.backend.workout.repositories.MuscleGroupRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseTemplateService {
    private final ExerciseTemplateRepository exerciseTemplateRepository;
    private final MuscleGroupRepository muscleGroupRepository;
    private final UserRepository userRepository;

    public ExerciseTemplateDTO createExerciseTemplate(CreateExerciseTemplateDTO dto) {
        Set<MuscleGroup> muscleGroups  = dto.getMuscleGroups().stream()
                .map(id -> muscleGroupRepository.getById(id)).collect(Collectors.toSet());

        User creator = userRepository.findById(dto.getCreatorId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        var currDate = LocalDateTime.now();
        ExerciseTemplate exerciseTemplate = ExerciseTemplate.builder()
                .creator(creator)
                .name(dto.getName())
                .description(dto.getDescription())
                .muscleGroups(muscleGroups)
                .media(dto.getMedia().stream()
                               .map(CreateExerciseTemplateMediaDTO::toEntity)
                               .collect(Collectors.toSet()))
                .createdAt(currDate)
                .updatedAt(currDate)
                .build();
        var created = exerciseTemplateRepository.save(exerciseTemplate);
        return ExerciseTemplateDTO.fromEntity(created);
    }

    public List<ExerciseTemplateDTO> getTemplates() {
        return exerciseTemplateRepository.findAll().stream()
                .map(ExerciseTemplateDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public ExerciseTemplate getTemplateById(Long id) {
        return exerciseTemplateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Exercise template not found"));
    }
}