package ru.nsu.dd.treuch.backend.workout.models;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum MuscleGroups {
    CHEST("Грудь"),
    BACK("Спина"),
    SHOULDERS("Плечи"),
    BICEPS("Бицепсы"),
    TRICEPS("Трицепсы"),
    FOREARMS("Предплечья"),
    ABS("Пресс"),
    OBLIQUES("Косые мышцы живота"),
    QUADRICEPS("Квадрицепсы"),
    HAMSTRINGS("Бицепсы бедра"),
    GLUTES("Ягодицы"),
    CALVES("Икры"),
    TRAPEZIUS("Трапеции"),
    LATS("Широчайшие"),
    DELTOIDS("Дельтовидные"),
    PECTORALS("Большие грудные"),
    SERRATUS("Зубчатые"),
    HIP_FLEXORS("Сгибатели бедра"),
    ADDUCTORS("Приводящие"),
    ABDUCTORS("Отводящие"),
    LOWER_BACK("Поясница"),
    NECK("Шея"),
    FULL_BODY("Все тело"),
    CARDIO("Кардио"),
    OTHER("Другое");

    private final String displayName;

    MuscleGroups(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static List<String> getAllDisplayNames() {
        return Arrays.stream(values())
                .map(MuscleGroups::getDisplayName)
                .collect(Collectors.toList());
    }

    public static MuscleGroups fromDisplayName(String displayName) {
        return Arrays.stream(values())
                .filter(group -> group.getDisplayName().equalsIgnoreCase(displayName))
                .findFirst()
                .orElse(OTHER);
    }
}