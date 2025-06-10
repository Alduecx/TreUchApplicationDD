package ru.nsu.dd.treuch.backend.workout.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.dd.treuch.backend.workout.dto.MuscleGroupDTO;
import ru.nsu.dd.treuch.backend.workout.services.MuscleGroupService;

import java.util.List;

@RestController
@RequestMapping("/muscle-groups")
@RequiredArgsConstructor
public class MuscleGroupController {
    private final MuscleGroupService muscleGroupService;

    @PostMapping("/batch")
    public ResponseEntity<?> createMuscleGroups(
            @Valid @RequestBody List<MuscleGroupDTO> groups) {
        try {
            var created = muscleGroupService.createMuscleGroups(groups);
            return ResponseEntity.status(HttpStatus.OK).body(created);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity<MuscleGroupDTO> createMuscleGroup(
            @Valid @RequestBody MuscleGroupDTO dto) {
        MuscleGroupDTO created = muscleGroupService.createMuscleGroup(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<?> getMuscleGroups(@PathVariable(required = false) Long muscleGroupsId) {
        try {
            if (muscleGroupsId == null) {
                return ResponseEntity.ok(muscleGroupService.getAllMuscleGroups());
            }
            return ResponseEntity.ok(muscleGroupService.getById(muscleGroupsId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}