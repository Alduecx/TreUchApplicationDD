package ru.nsu.dd.treuch.backend.workout.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nsu.dd.treuch.backend.workout.models.Report;
import ru.nsu.dd.treuch.backend.workout.models.Workout;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO {
    private String description;
    private Integer duration;

    static public ReportDTO fromEntity(Report report) {
        return ReportDTO.builder()
                .description(report.getDescription())
                .duration(report.getDuration())
                .build();
    }
    static public Report toEntity(ReportDTO dto, Workout workout) {
        return Report.builder()
                .description(dto.getDescription())
                .duration(dto.getDuration())
                .workout(workout)
                .build();
    }
}
