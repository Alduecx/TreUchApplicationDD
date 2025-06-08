package ru.nsu.dd.treuch.backend.workout.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "exercise_template_media")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseTemplateMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileUrl; // Например: "/uploads/image.jpg"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MediaType type; // IMAGE или VIDEO

    public enum MediaType {
        IMAGE, VIDEO
    }
}