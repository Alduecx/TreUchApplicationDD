package ru.nsu.dd.treuch.backend.workout.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.dd.treuch.backend.workout.models.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
}
