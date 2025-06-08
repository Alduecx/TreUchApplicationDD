package ru.nsu.dd.treuch.backend.workout.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nsu.dd.treuch.backend.workout.models.ExerciseTemplateMedia;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseTemplateMediaDTO {
    private Long id;
    private String fileUrl;
    private ExerciseTemplateMedia.MediaType type;

    public static ExerciseTemplateMediaDTO fromEntity(ExerciseTemplateMedia templateMedia) {
        return ExerciseTemplateMediaDTO.builder()
                .id(templateMedia.getId())
                .fileUrl(templateMedia.getFileUrl())
                .type(templateMedia.getType())
                .build();
    }
}