package com.college.college_management_system.controller;

import com.college.college_management_system.dto.LoginRequest;
import com.college.college_management_system.dto.LoginResponse;
import com.college.college_management_system.dto.RegisterRequest;
import com.college.college_management_system.entity.User;
import com.college.college_management_system.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    /* =========================
       LOGIN
    ========================= */

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request) {

        return ResponseEntity.ok(
                authService.login(request)
        );
    }


    /* =========================
       STUDENT REGISTRATION
    ========================= */

    @PostMapping("/register")
    public ResponseEntity<User> register(
            @Valid @RequestBody RegisterRequest request) {

        return new ResponseEntity<>(
                authService.registerStudent(request),
                HttpStatus.CREATED
        );
    }
}