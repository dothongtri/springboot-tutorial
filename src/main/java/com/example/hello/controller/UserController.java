// package com.example.hello.controller;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.hello.dto.UserResponse;
// import com.example.hello.service.UserService;

// @RestController
// @RequestMapping("/api/user")
// public class UserController {

// @Autowired
// private UserService userService;

// @GetMapping("list")
// public ResponseEntity<List<UserResponse>> getAllUsers() {
// List<UserResponse> users = userService.getAllUsers();
// return ResponseEntity.status(201).body(users);
// }
// }
