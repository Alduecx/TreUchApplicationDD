package ru.nsu.dd.treuch.backend.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ru.nsu.dd.treuch.backend.security.model.UserRole;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticationRequest {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private String passwordHash;
    private UserRole role;
    private String refreshToken;
}