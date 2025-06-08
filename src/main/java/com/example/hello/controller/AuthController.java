package com.example.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hello.dto.AuthRequest;
import com.example.hello.dto.AuthResponse;
import com.example.hello.dto.GoogleLoginRequest;
import com.example.hello.dto.UserRequest;
import com.example.hello.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserRequest request) {
        String message = userService.createUser(request); // Gọi service để tạo user mới

        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest request) {
        AuthResponse response =  userService.login(request.getEmail(), request.getPassword());

        return ResponseEntity.ok(response);
    }

    @PostMapping("google")
    public ResponseEntity<?> googleLogin(@Valid @RequestBody GoogleLoginRequest request) {
        String idToken = request.getToken();
        AuthResponse response =  userService.loginWithGoogle(idToken);
        return ResponseEntity.ok(response);
    }

}
