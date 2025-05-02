package com.example.fitgo.model;

public class User {
    private String fullName;
    private String email;
    private String username;
    private String password;

    // Constructor
    public User(String fullName, String email, String username, String password) {
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    // Getters and setters (optional if using Gson)
}
