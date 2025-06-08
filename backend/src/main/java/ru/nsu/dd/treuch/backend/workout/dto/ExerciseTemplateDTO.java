package ru.nsu.dd.treuch.backend.workout.dto;

import lombok.*;
import ru.nsu.dd.treuch.backend.workout.models.ExerciseTemplate;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseTemplateDTO {
    private String name;
    private Long creatorId;
    private Set<MuscleGroupDTO> muscleGroups;
    private String description;
    private Set<ExerciseTemplateMediaDTO> media;

    public static ExerciseTemplateDTO fromEntity(ExerciseTemplate template) {
        return ExerciseTemplateDTO.builder()
                .name(template.getName())
                .creatorId(template.getCreator().getId())
                .muscleGroups(template.getMuscleGroups()
                                      .stream()
                                      .map(MuscleGroupDTO::fromEntity)
                                      .collect(Collectors.toSet()))
                .description(template.getDescription())
                .media(template.getMedia()
                               .stream().map(ExerciseTemplateMediaDTO::fromEntity)
                               .collect(Collectors.toSet()))
                .build();
    }
}