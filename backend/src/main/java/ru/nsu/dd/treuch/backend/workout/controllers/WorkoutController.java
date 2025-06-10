package ru.nsu.dd.treuch.backend.workout.controllers;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.dd.treuch.backend.workout.dto.WorkoutDTO;
import ru.nsu.dd.treuch.backend.security.JWTUtils;
import ru.nsu.dd.treuch.backend.workout.services.WorkoutService;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/workouts")
@RequiredArgsConstructor
public class WorkoutController {
    private final WorkoutService workoutService;
    private final JWTUtils jwtService;

    @PostMapping
    public ResponseEntity<?> createWorkout(
            @RequestHeader("Authorization") String authToken,
            @RequestBody WorkoutDTO workoutDTO) {
        try {
            String requesterEmail = getRequesterEmail(authToken);

            workoutService.createWorkout(workoutDTO, requesterEmail);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<?> getClientWorkouts(
            @RequestHeader("Authorization") String authToken,
            @RequestParam(required = false) Long clientId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate
    ) {
        try {
            String requesterEmail = getRequesterEmail(authToken);

            List<WorkoutDTO> workouts = workoutService.getClientWorkouts(
                    requesterEmail,
                    clientId,
                    fromDate,
                    toDate
            );

            return ResponseEntity.ok(workouts);
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity<WorkoutDTO> updateWorkout(
            @RequestHeader("Authorization") String authToken,
            @PathVariable Long workoutId,
            @RequestBody WorkoutDTO dto
    ) {
        String requesterEmail =  getRequesterEmail(authToken);

        WorkoutDTO updated = workoutService.updateWorkout(workoutId, dto, requesterEmail);
        return ResponseEntity.ok(updated);
    }

    private String getRequesterEmail(String authToken) {
        String token = authToken.substring(7);
        return jwtService.extractUsername(token);
    }


}