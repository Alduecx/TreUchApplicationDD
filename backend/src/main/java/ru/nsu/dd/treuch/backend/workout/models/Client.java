package ru.nsu.dd.treuch.backend.workout.models;

import jakarta.persistence.*;
import lombok.*;
import ru.nsu.dd.treuch.backend.security.model.User;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", unique = true)
    private User user;
}
