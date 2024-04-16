package com.pixels.Nexum.model;

public class AuthenticationResponse {
    private String token;
    private String message;

    private User user;

    public AuthenticationResponse(String token, String message, User user) {
        this.token = token;
        this.message = message;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}
