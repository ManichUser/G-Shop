package com.example.authentification_test.controller;


import com.example.authentification_test.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @PostMapping("/me")
    public ResponseEntity<?>me(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(user);
    }
}
