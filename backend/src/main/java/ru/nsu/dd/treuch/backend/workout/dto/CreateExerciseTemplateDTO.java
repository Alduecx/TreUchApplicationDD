package ru.nsu.dd.treuch.backend.workout.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateExerciseTemplateDTO {
    private String name;
    private Long creatorId;
    private Set<Long> muscleGroups;
    private String description;
    private Set<CreateExerciseTemplateMediaDTO> media;
}