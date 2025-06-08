package ru.nsu.dd.treuch.backend.workout.dto;

import lombok.*;
import ru.nsu.dd.treuch.backend.workout.models.MuscleGroup;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MuscleGroupDTO {
    private Long id;
    private String name;
    private String displayName;

    public static MuscleGroupDTO fromEntity(MuscleGroup muscleGroup) {
        return MuscleGroupDTO.builder()
                .id(muscleGroup.getId())
                .name(muscleGroup.getName())
                .displayName(muscleGroup.getDisplayName())
                .build();
    }

    public MuscleGroup toEntity() {
        return MuscleGroup.builder()
                .name(this.name)
                .displayName(this.displayName)
                .build();
    }
}