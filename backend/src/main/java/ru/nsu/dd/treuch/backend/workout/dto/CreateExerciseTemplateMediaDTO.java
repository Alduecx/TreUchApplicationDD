package ru.nsu.dd.treuch.backend.workout.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nsu.dd.treuch.backend.workout.models.ExerciseTemplate;
import ru.nsu.dd.treuch.backend.workout.models.ExerciseTemplateMedia;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateExerciseTemplateMediaDTO {
    private String fileUrl;
    private ExerciseTemplateMedia.MediaType type;

    public static ExerciseTemplateMedia toEntity(CreateExerciseTemplateMediaDTO dto) {
        return ExerciseTemplateMedia.builder()
                .fileUrl(dto.getFileUrl())
                .type(dto.getType())
                .build();
    }
}