package com.bishal.authentication.controllers;

import com.bishal.authentication.dto.response.ApiResponse;
import com.bishal.authentication.dto.response.UserDTO;
import com.bishal.authentication.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name="User", description="User API(s)")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary="User List", description="List all users", security={@SecurityRequirement(name="bearer-key")})
    @GetMapping
    public ResponseEntity<ApiResponse> getUsers() {
        List<UserDTO> users = this.userService.getAllUsers();
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK.value(), false, "Users fetched successfully!", users);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Operation(summary="User Details", description="Get user details", security={@SecurityRequirement(name="bearer-key")})
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse> getUserById(@Parameter(description="Identifier key of the user", required = true) @PathVariable("userId") Long userId) {
        try {
            UserDTO user = this.userService.getUserById(userId);
            ApiResponse apiResponse = new ApiResponse(HttpStatus.OK.value(), false, "User fetched successfully!", user);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        catch(Exception e) {
            ApiResponse apiResponse = new ApiResponse(HttpStatus.NOT_FOUND.value(), true, "User not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }

    }
}
