package ru.nsu.dd.treuch.backend.workout.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.dd.treuch.backend.workout.models.ExerciseTemplate;

public interface ExerciseTemplateRepository extends JpaRepository<ExerciseTemplate, Long> {
}