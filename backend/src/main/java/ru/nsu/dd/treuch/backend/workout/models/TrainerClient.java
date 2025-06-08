package ru.nsu.dd.treuch.backend.workout.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "trainer_client")
public class TrainerClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "assigned_at", updatable = false)
    private LocalDateTime assignedAt = LocalDateTime.now();
}