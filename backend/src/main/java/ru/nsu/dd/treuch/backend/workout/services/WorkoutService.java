package ru.nsu.dd.treuch.backend.workout.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.dd.treuch.backend.workout.dto.ExerciseDTO;
import ru.nsu.dd.treuch.backend.workout.dto.SetDTO;
import ru.nsu.dd.treuch.backend.workout.dto.WorkoutDTO;
import ru.nsu.dd.treuch.backend.workout.models.*;
import ru.nsu.dd.treuch.backend.workout.repositories.ClientRepository;
import ru.nsu.dd.treuch.backend.workout.repositories.TrainerClientRepository;
import ru.nsu.dd.treuch.backend.workout.repositories.TrainerRepository;
import ru.nsu.dd.treuch.backend.workout.repositories.WorkoutRepository;
import ru.nsu.dd.treuch.backend.security.model.User;
import ru.nsu.dd.treuch.backend.security.repositories.UserRepository;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final ExerciseTemplateService exerciseTemplateService;
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final TrainerRepository trainerRepository;
    private final TrainerClientRepository trainerClientRepository;


    @Transactional
    public void createWorkout(WorkoutDTO workoutDTO, String email) {
        User workoutCreator = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Client client = clientRepository.findById(workoutDTO.getClientId()).orElseThrow(() -> new EntityNotFoundException("Client not found"));
        if (!workoutCreator.getId().equals(client.getId())) {
            throw new IllegalArgumentException("Client is not the owner of this workout");
        }

        Trainer trainer = trainerRepository.findById(workoutDTO.getTrainerId()).orElse(null);
        Workout workout = Workout.builder()
                .name(workoutDTO.getName())
                .client(client)
                .trainer(trainer)
                .date(LocalDate.parse(workoutDTO.getDate()))
                .status(WorkoutStatus.valueOf(workoutDTO.getStatus()))
                .build();

        List<Exercise> exercises = workoutDTO.getExercises().stream()
                .map(exDto -> createExercise(exDto, workout))
                .collect(Collectors.toList());

        workout.setExercises(exercises);
        workoutRepository.save(workout);
    }

    public List<WorkoutDTO> getClientWorkouts(
            String requesterEmail,
            Long clientId,
            LocalDate fromDate,
            LocalDate toDate
    ) throws AccessDeniedException {
        User requester = userRepository.findByEmail(requesterEmail).orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (clientId == null) {
            // Проверяем, что запрашивающий пользователь является клиентом
            Client client = clientRepository.findByUserId(requester.getId()).orElseThrow(() -> new EntityNotFoundException("Client not found"));
            clientId = client.getId();
        } else {
            // Проверяем, что запрашивающий пользователь является тренером. И он является тренером для указанного клиента
            Trainer trainer = trainerRepository.findById(requester.getId()).orElseThrow(() -> new EntityNotFoundException("Trainer not found"));
            trainerClientRepository.findByClientIdAndTrainerId(clientId, trainer.getId()).orElseThrow(() -> new EntityNotFoundException("Trainer does not have access to this client"));
        }

        // Получаем тренировки с фильтрацией по дате
        List<Workout> workouts;
        if (fromDate != null && toDate != null) {
            workouts = workoutRepository.findByClientIdAndDateBetween(clientId, fromDate, toDate);
        } else if (fromDate != null) {
            workouts = workoutRepository.findByClientIdAndDateAfter(clientId, fromDate);
        } else if (toDate != null) {
            workouts = workoutRepository.findByClientIdAndDateBefore(clientId, toDate);
        } else {
            workouts = workoutRepository.findByClientId(clientId);
        }

        return workouts.stream()
                .map(WorkoutDTO::fromEntity)
                .collect(Collectors.toList());
    }


    private Exercise createExercise(ExerciseDTO exerciseDTO, Workout workout) {
        ExerciseTemplate template = exerciseTemplateService.getTemplateById(exerciseDTO.getTemplateId());

        Exercise exercise = Exercise.builder()
                .workout(workout)
                .number(exerciseDTO.getNumber())
                .template(template)
                .comment(exerciseDTO.getComment())
                .status(ExerciseStatus.valueOf(exerciseDTO.getStatus()))
                .build();

        List<Set> sets = exerciseDTO.getSets().stream()
                .map(setDto -> createSet(setDto, exercise))
                .collect(Collectors.toList());

        exercise.setSets(sets);
        return exercise;
    }

    private Set createSet(SetDTO setDTO, Exercise exercise) {
        return Set.builder()
                .exercise(exercise)
                .setNumber(setDTO.getSetNumber())
                .weight(setDTO.getWeight())
                .duration(setDTO.getDuration())
                .restTime(setDTO.getRestTime())
                .build();
    }
}