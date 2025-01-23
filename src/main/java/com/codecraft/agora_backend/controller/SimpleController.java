package com.codecraft.agora_backend.controller;


import com.codecraft.agora_backend.model.User;
import com.codecraft.agora_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Test controller to check authentication and authorization
 */
@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:3000")
public class SimpleController {

    @Autowired
    private UserRepository userRepository;

    /**
     * This endpoint checks if the logged in user has the role of ADMIN
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<String> helloAdmin(){
        return ResponseEntity.ok("Hello Admin");
    }

    /**
     * This endpoint checks if the logged in user has the role of USER
     */
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public ResponseEntity<String> helloUser(){
        return ResponseEntity.ok("Hello User");
    }

    /**
     * This endpoint returns all the users in the database, only accessible if logged in
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users-old")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }
}
