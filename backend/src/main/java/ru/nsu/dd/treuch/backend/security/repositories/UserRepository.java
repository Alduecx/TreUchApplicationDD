package ru.nsu.dd.treuch.backend.security.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.dd.treuch.backend.security.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByResetToken(String resetToken);
}