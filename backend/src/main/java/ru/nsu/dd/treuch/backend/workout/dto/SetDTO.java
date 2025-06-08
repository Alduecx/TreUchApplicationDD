package ru.nsu.dd.treuch.backend.workout.dto;

import lombok.*;
import ru.nsu.dd.treuch.backend.workout.models.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SetDTO {
    private Integer setNumber;
    private Float weight;
    private Integer duration;
    private Integer restTime;

    public static SetDTO fromEntity(Set set) {
        return SetDTO.builder()
                .setNumber(set.getSetNumber())
                .weight(set.getWeight())
                .duration(set.getDuration())
                .restTime(set.getRestTime())
                .build();
    }
}