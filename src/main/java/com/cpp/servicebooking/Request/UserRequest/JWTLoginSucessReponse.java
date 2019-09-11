package com.cpp.servicebooking.Request.UserRequest;

import com.cpp.servicebooking.models.User;

public class JWTLoginSucessReponse {
    private String token;
    private User user;

    public JWTLoginSucessReponse(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "JWTLoginSucessReponse{" +
                "token='" + token + '\'' +
                '}';
    }
}
