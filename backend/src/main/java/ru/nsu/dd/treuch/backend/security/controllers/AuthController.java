package ru.nsu.dd.treuch.backend.security.controllers;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import ru.nsu.dd.treuch.backend.security.dto.AuthenticationRequest;
import ru.nsu.dd.treuch.backend.security.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register") // Регистрация
    public ResponseEntity<?> register(@RequestBody AuthenticationRequest request) {
        try {
            return ResponseEntity.status(201).body(authService.register(request));
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(406).body(e.getMessage());
        } catch (DuplicateKeyException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }
    @PostMapping("/login") // Аутентификация
    public ResponseEntity<?> logIn(@RequestBody AuthenticationRequest request){
        try {
            return ResponseEntity.ok(authService.logIn(request));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }

}