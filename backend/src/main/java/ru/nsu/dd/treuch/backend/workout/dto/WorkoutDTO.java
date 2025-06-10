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
    private Long id;
    private String name;
    private Long trainerId;
    private Long clientId;
    private String startDateTime;
    private String endDateTime;
    private String status;
    private List<ExerciseDTO> exercises;

    public static WorkoutDTO fromEntity(Workout workout) {
        return WorkoutDTO.builder()
                .id(workout.getId())
                .name(workout.getName())
                .trainerId(workout.getTrainer().getId())
                .clientId(workout.getClient().getId())
                .startDateTime(workout.getStartDateTime().toString())
                .endDateTime(workout.getEndDateTime().toString())
                .status(workout.getStatus().name())
                .exercises(workout.getExercises().stream()
                        .map(ExerciseDTO::fromEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}