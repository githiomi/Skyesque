package com.alu.skyesque.models;

/**
 * Author: dangit
 * Project Name: Skyesque
 * Name: Daniel Githiomi
 * Student Id: S2110911
 * Programme of Study: Computing
 * Date of creation: Sunday 31, 03 2024
 */
public enum User {

    DGITH200("DGITH200", "Daniel", "Githiomi", "Student", "daniel123", "dgithi200@caledonian.ac.uk"),
    ABART999("ABART999", "Allan", "Bartolome", "Faculty", "allan321", "abart999@caledonian.ac.uk");

    private String username;
    private String firstName;
    private String lastName;
    private String role;
    private String password;
    private String email;

    User(String username, String firstName, String lastName, String role, String password, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
