package com.bishal.authentication.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="User", description="User API(s)")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Operation(summary="User List", description="List all users", security={@SecurityRequirement(name="bearer-key")})
    @GetMapping
    public String getUsers() {
        return "User list";
    }

    @Operation(summary="User Details", description="Get user details", security={@SecurityRequirement(name="bearer-key")})
    @GetMapping("/{userId}")
    public String getUserById(@Parameter(description="Identifier key of the user", required = true) @PathVariable("userId") Long userId) {
        return "User by id: " + userId;
    }
}
