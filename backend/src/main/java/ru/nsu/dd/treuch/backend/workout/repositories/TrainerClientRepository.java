package ru.nsu.dd.treuch.backend.workout.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.dd.treuch.backend.workout.models.TrainerClient;

import java.util.List;
import java.util.Optional;

public interface TrainerClientRepository  extends JpaRepository<TrainerClient, Long> {
    List<TrainerClient> findByClientId(Long clientId);
    Optional<TrainerClient> findByClientIdAndTrainerId(Long clientId, Long trainerId);
    List<TrainerClient> findByTrainerId(Long clientId);
}
