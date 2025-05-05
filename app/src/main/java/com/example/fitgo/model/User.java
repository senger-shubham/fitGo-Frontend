package com.example.fitgo.model;

public class User {
    private String fullName;
    private String email;
    private String username;
    private String password;

    // Full constructor (for registration)
    public User(String fullName, String email, String username, String password) {
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    // Constructor for login (email & password only)
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Empty constructor (required for deserialization)
    public User() {
    }

    // Getters and Setters
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
