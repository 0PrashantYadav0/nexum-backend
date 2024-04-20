package com.pixels.Nexum.service;


import com.pixels.Nexum.model.AuthenticationResponse;
import com.pixels.Nexum.model.Role;
import com.pixels.Nexum.model.Token;
import com.pixels.Nexum.model.User;
import com.pixels.Nexum.repository.TokenRepository;
import com.pixels.Nexum.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final TokenRepository tokenRepository;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository repository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 TokenRepository tokenRepository,
                                 AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(User request) {

        // check if user already exist. if exist than authenticate the user
        if(repository.findByUsername(request.getUsername()).isPresent()) {
            return new AuthenticationResponse(null, "User already exist", null);
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhotoUrl(request.getPhotoUrl());
        user.setRole(request.getRole());

        user = repository.save(user);

        String jwt = jwtService.generateToken(user);

        saveUserToken(jwt, user);

        return new AuthenticationResponse(jwt, "User registration was successful", user);

    }

    public AuthenticationResponse authenticate(User request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = repository.findByUsername(request.getUsername()).orElseThrow();
        String jwt = jwtService.generateToken(user);

        revokeAllTokenByUser(user);
        saveUserToken(jwt, user);

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setRole(user.getRole());
        newUser.setId(user.getId());
        newUser.setPhotoUrl(user.getPhotoUrl());
        newUser.setPhoneNumber(user.getPhoneNumber());


        return new AuthenticationResponse(jwt, "User login was successful", newUser);

    }

    public AuthenticationResponse google(User request) {
        if(repository.findByUsername(request.getUsername()).isPresent()) {
            Optional<User> user = repository.findByUsername(request.getUsername());
            User newUser = new User();
            newUser.setUsername(user.get().getUsername());
            newUser.setEmail(user.get().getEmail());
            newUser.setRole(user.get().getRole());
            newUser.setId(user.get().getId());
            newUser.setPhotoUrl(user.get().getPhotoUrl());
            newUser.setPhoneNumber(user.get().getPhoneNumber());
            String jwt = jwtService.generateToken(newUser);
            saveUserToken(jwt, newUser);
            return new AuthenticationResponse(jwt, "User already exist", newUser);
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPhotoUrl(request.getPhotoUrl());

        String generatedPassword =
                UUID.randomUUID().toString().substring(0, 8) +
                        UUID.randomUUID().toString().substring(0, 8);

        user.setPassword(passwordEncoder.encode(generatedPassword));


        user.setRole(request.getRole());

        user = repository.save(user);

        String jwt = jwtService.generateToken(user);

        saveUserToken(jwt, user);

        return new AuthenticationResponse(jwt, "User registration was successful", user);

    }

    public User getUser(Integer id) {
        return repository.findById(id).orElse(null);
    }

    private void revokeAllTokenByUser(User user) {
        List<Token> validTokens = tokenRepository.findAllTokensByUser(user.getId());
        if(validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t-> {
            t.setLoggedOut(true);
        });

        tokenRepository.saveAll(validTokens);
    }
    private void saveUserToken(String jwt, User user) {
        Token token = new Token();
        token.setToken(jwt);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepository.save(token);
    }

    public AuthenticationResponse logout(String token) {
        Token tokens = tokenRepository.findByToken(token).orElse(null);
        if(tokens == null) {
            return new AuthenticationResponse(null, "Token not found", null);
        }
        tokens.setLoggedOut(true);
        tokenRepository.save(tokens);
        return new AuthenticationResponse(null, "User logout was successful", null);
    }

    public AuthenticationResponse update(Integer id, User request) {
        User user = repository.findById(id).orElse(null);
        if(user == null) {
            return new AuthenticationResponse(null, "User not found", null);
        }
        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setPhoneNumber(request.getPhoneNumber());
        newUser.setPhotoUrl(request.getPhotoUrl());
        newUser.setId(id);
        newUser.setRole(Role.valueOf("USER"));
        newUser = repository.save(newUser);
        String jwt = jwtService.generateToken(newUser);
        saveUserToken(jwt, newUser);
        return new AuthenticationResponse(jwt, "User update was successful", newUser);
    }
}
