package ru.nsu.dd.treuch.backend.workout.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.dd.treuch.backend.workout.dto.CreateExerciseTemplateDTO;
import ru.nsu.dd.treuch.backend.workout.dto.ExerciseTemplateDTO;
import ru.nsu.dd.treuch.backend.workout.services.ExerciseTemplateService;

import java.util.List;

@RestController
@RequestMapping("/exercise-templates")
@RequiredArgsConstructor
public class ExerciseTemplateController {
    private final ExerciseTemplateService exerciseTemplateService;

    @GetMapping
    public ResponseEntity<?> getExerciseTemplates(@RequestHeader("Authorization") String authToken) {
        try {
            List<ExerciseTemplateDTO> templates = exerciseTemplateService.getTemplates();
            return ResponseEntity.ok(templates);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<ExerciseTemplateDTO> createTemplate(@RequestBody CreateExerciseTemplateDTO template) {
        try {
            var created = exerciseTemplateService.createExerciseTemplate(template);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}