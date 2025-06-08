package ru.nsu.dd.treuch.backend.workout.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.dd.treuch.backend.workout.models.Set;

import java.util.List;

public interface SetRepository extends JpaRepository<Set, Long> {
    List<Set> findByExerciseId(Long exerciseId);
}