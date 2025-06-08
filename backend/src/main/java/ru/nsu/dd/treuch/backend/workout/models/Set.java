package ru.nsu.dd.treuch.backend.workout.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Set {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @Column(name = "set_number", nullable = false)
    private Integer setNumber;

    @Column(nullable = false)
    private Float weight;

    @Column(nullable = false)
    private Integer duration; // в секундах

    @Column(name = "rest_time", nullable = false)
    private Integer restTime; // в секундах
}