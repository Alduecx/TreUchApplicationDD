package ru.nsu.dd.treuch.backend.security.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import ru.nsu.dd.treuch.backend.security.JWTUtils;
import ru.nsu.dd.treuch.backend.security.dto.AuthenticationRequest;
import ru.nsu.dd.treuch.backend.security.dto.AuthenticationResponse;
import ru.nsu.dd.treuch.backend.security.model.User;
import ru.nsu.dd.treuch.backend.security.repositories.UserRepository;
import ru.nsu.dd.treuch.backend.workout.models.Client;
import ru.nsu.dd.treuch.backend.workout.models.Trainer;
import ru.nsu.dd.treuch.backend.workout.repositories.ClientRepository;
import ru.nsu.dd.treuch.backend.workout.repositories.TrainerRepository;

import java.util.HashMap;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Transactional
    public AuthenticationResponse register(AuthenticationRequest registrationRequest){
        AuthenticationResponse response = new AuthenticationResponse();
        User user = new User();
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());
        user.setPatronymic(registrationRequest.getPatronymic());
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(registrationRequest.getPasswordHash());
        user.setRole(registrationRequest.getRole());
        try {
            switch (user.getRole()) {
                case CLIENT -> {
                    var client = new Client();
                    client.setUser(user);
                    clientRepository.save(client);
                }
                case TRAINER -> {
                    var trainer = new Trainer();
                    trainer.setUser(user);
                    trainerRepository.save(trainer);
                }
                default -> {
                    throw new ConstraintViolationException("Unknown role", null);
                }
            }
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateKeyException("Пользователь с таким email уже зарегистрирован");
        } catch (ConstraintViolationException e) {
            throw new ConstraintViolationException("Некорректные данные", e.getConstraintViolations());
        }
        if (user.getId() > 0) {
            createResponse(response, user);
        }
        return response;
    }

    @Transactional
    public AuthenticationResponse logIn(AuthenticationRequest authorizationRequest) throws AuthenticationException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authorizationRequest.getEmail(), authorizationRequest.getPasswordHash()));
            AuthenticationResponse response = new AuthenticationResponse();
            var user = userRepository.findByEmail(authorizationRequest.getEmail())
                    .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
            createResponse(response, user);
            return response;
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Неверный email или пароль");
        } catch (AuthenticationException e) {
            // Общая обработка других ошибок аутентификации
            throw new AuthenticationServiceException("Ошибка аутентификации", e);

        }
    }

    private void createResponse(AuthenticationResponse response, User user) {
        var jwt = jwtUtils.generateToken(user);
        var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
        response.setUserId(user.getId());
        response.setRole(user.getRole());
        response.setToken(jwt);
        response.setRefreshToken(refreshToken);
    }
}