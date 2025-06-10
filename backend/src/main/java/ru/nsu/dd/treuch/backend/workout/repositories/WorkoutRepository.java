package ru.nsu.dd.treuch.backend.workout.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.dd.treuch.backend.workout.models.Workout;

import java.time.LocalDate;
import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByClientId(Long clientId);
    List<Workout> findByClientIdAndStartDateTimeBetween(Long clientId, LocalDate fromDate, LocalDate toDate);
    List<Workout> findByClientIdAndStartDateTimeAfter(Long clientId, LocalDate fromDate);
    List<Workout> findByClientIdAndStartDateTimeBefore(Long clientId, LocalDate toDate);
}