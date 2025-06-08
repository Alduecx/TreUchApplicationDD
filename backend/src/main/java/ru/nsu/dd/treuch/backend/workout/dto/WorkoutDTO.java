package ru.nsu.dd.treuch.backend.workout.dto;

import lombok.*;
import ru.nsu.dd.treuch.backend.workout.models.Workout;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutDTO {
    private String name;
    private Long trainerId;
    private Long clientId;
    private String date;
    private String status;
    private List<ExerciseDTO> exercises;

    public static WorkoutDTO fromEntity(Workout workout) {
        return WorkoutDTO.builder()
                .name(workout.getName())
                .trainerId(workout.getTrainer().getId())
                .clientId(workout.getClient().getId())
                .date(workout.getDate().toString())
                .status(workout.getStatus().name())
                .exercises(workout.getExercises().stream()
                        .map(ExerciseDTO::fromEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}