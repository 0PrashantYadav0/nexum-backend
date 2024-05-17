package com.pixels.Nexum.controller;

import com.pixels.Nexum.model.AuthenticationResponse;
import com.pixels.Nexum.model.Token;
import com.pixels.Nexum.model.User;
import com.pixels.Nexum.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/")
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }


    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody User request
            ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody User request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("google")
    public ResponseEntity<AuthenticationResponse> google(
            @RequestBody User request
    ) {
        return ResponseEntity.ok(authService.google(request));
    }

    @GetMapping("logout")
    public ResponseEntity<AuthenticationResponse> logout(
            @RequestHeader("Authorization") String token
    ) {
        return ResponseEntity.ok(authService.logout(token));
    }

    @PostMapping("update/{id}")
    public ResponseEntity<AuthenticationResponse> update(
            @PathVariable("id") Integer id , @RequestBody User request
    ) {
        return ResponseEntity.ok(authService.update(id, request));
    }

    @GetMapping("getUser/{id}")
    public ResponseEntity<User> getUser(
            @PathVariable("id") Integer id
    ) {
        return ResponseEntity.ok(authService.getUser(id));
    }

}
