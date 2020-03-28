package com.example.adorablepet.model;

public class User {
    private String username;
    private String password;

    public User() {}

    public User(String password, String username) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
