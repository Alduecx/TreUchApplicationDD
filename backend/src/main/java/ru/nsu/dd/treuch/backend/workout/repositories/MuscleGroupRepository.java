package ru.nsu.dd.treuch.backend.workout.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.dd.treuch.backend.workout.models.MuscleGroup;

import java.util.Optional;

public interface MuscleGroupRepository extends JpaRepository<MuscleGroup, Long> {
    Optional<MuscleGroup> findByName(String name);
    boolean existsByName(String name);
}