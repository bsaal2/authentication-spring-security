package com.bishal.authentication.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="authUser")
public class User {
    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank(message="Firstname can not be empty")
    private String firstName;

    @NotBlank(message="Lastname can not be empty")
    private String lastName;

    @Email
    private String email;

    @NotBlank(message="Password can not be empty")
    private String password;

    // Empty constructor
    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
