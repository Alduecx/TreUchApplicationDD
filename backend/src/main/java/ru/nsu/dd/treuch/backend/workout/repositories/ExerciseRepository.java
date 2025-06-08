package ru.nsu.dd.treuch.backend.workout.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.dd.treuch.backend.workout.models.Exercise;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findByWorkoutId(Long workoutId);
}