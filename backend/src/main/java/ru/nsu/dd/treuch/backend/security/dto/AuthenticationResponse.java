package ru.nsu.dd.treuch.backend.security.dto;

import lombok.Data;
import ru.nsu.dd.treuch.backend.security.model.UserRole;

@Data
public class AuthenticationResponse {
    private Long userId;
    private String token;
    private String refreshToken;
    private UserRole role;
}