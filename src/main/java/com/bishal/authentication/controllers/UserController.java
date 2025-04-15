package com.bishal.authentication.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping
    public String getUsers() {
        return "User list";
    }

    @GetMapping("/{userId}")
    public String getUserById(@PathVariable("userId") Long userId) {
        return "User by id: " + userId;
    }
}
