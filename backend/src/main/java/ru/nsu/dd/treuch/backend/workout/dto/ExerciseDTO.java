package ru.nsu.dd.treuch.backend.workout.dto;

import lombok.*;
import ru.nsu.dd.treuch.backend.workout.models.Exercise;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseDTO {
    private Integer number;
    private Long templateId;
    private String comment;
    private String status;
    private List<SetDTO> sets;

    public static ExerciseDTO fromEntity(Exercise exercise) {
        return ExerciseDTO.builder()
                .number(exercise.getNumber())
                .templateId(exercise.getTemplate().getId())
                .comment(exercise.getComment())
                .status(exercise.getStatus().name())
                .sets(exercise.getSets().stream()
                        .map(SetDTO::fromEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}