package com.bishal.authentication.controllers;

import com.bishal.authentication.dto.request.UserLoginDTO;
import com.bishal.authentication.dto.request.UserRegisterDTO;
import com.bishal.authentication.dto.response.ApiResponse;
import com.bishal.authentication.models.User;
import com.bishal.authentication.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> doUserLogin(@Valid @RequestBody UserLoginDTO userLoginDTO, Errors errors) {
        if (errors.hasErrors()) {
            System.out.println(errors.getAllErrors().toString());
            ApiResponse apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST.value(), true, "Bad request", null);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        try {
            this.userService.doLogin(userLoginDTO);
            ApiResponse apiResponse = new ApiResponse(HttpStatus.OK.value(), false, "User loggedIn successfully!", null);
            return ResponseEntity.ok(apiResponse);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            ApiResponse apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST.value(), true, "User not found", null);
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> doUserRegister(@Valid @RequestBody UserRegisterDTO userDTO, Errors errors) {
        System.out.println(errors.getAllErrors());
        if (errors.hasErrors()) {
            ApiResponse apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST.value(), true, errors.getAllErrors().toString(), null);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        User user = this.userService.registerNewUser(userDTO);
        ApiResponse apiResponse = new ApiResponse(HttpStatus.CREATED.value(), false, "User registered successfully!", user);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}
