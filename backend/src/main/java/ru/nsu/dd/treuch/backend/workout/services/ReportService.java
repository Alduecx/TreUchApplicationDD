package ru.nsu.dd.treuch.backend.workout.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.dd.treuch.backend.workout.dto.ReportDTO;
import ru.nsu.dd.treuch.backend.workout.models.Report;
import ru.nsu.dd.treuch.backend.workout.models.Workout;
import ru.nsu.dd.treuch.backend.workout.repositories.ReportRepository;
import ru.nsu.dd.treuch.backend.workout.repositories.WorkoutRepository;


@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final WorkoutRepository workoutRepository;

    public ReportDTO saveReport(ReportDTO dto, Long workoutId) {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new EntityNotFoundException("Workout not found"));
        Report report = ReportDTO.toEntity(dto, workout);
        var created = reportRepository.save(report);
        return ReportDTO.fromEntity(created);
    }
}
