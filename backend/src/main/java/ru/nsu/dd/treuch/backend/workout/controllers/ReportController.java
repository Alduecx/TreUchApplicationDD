package ru.nsu.dd.treuch.backend.workout.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.dd.treuch.backend.workout.dto.ReportDTO;
import ru.nsu.dd.treuch.backend.workout.services.ReportService;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @PostMapping
    public ResponseEntity<?> createReport(
            @PathVariable Long workoutId,
            @RequestBody ReportDTO reportDTO) {
        try {
            var created = reportService.saveReport(reportDTO, workoutId);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
