package com.example.authentification_test.controller;


import com.example.authentification_test.dto.AuthResponse;
import com.example.authentification_test.dto.LoginRequest;
import com.example.authentification_test.dto.RegisterRequest;
import com.example.authentification_test.entities.Role;
import com.example.authentification_test.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    //Endpoint d'inscription pour un user ou producer

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        AuthResponse response = authService.registerUser(request);

        return ResponseEntity.ok(response);
    }

    //Endpoint pour connexion avec email ou mot de passe
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        AuthResponse response = authService.authenticateUser(request);
        return ResponseEntity.ok(response);
    }

    //Endpoint pour le switch role

    @PostMapping("/switch-role")
    public ResponseEntity<AuthResponse> switchRole(
            @RequestParam("userId") Long userId,
            @RequestParam("role")Role newRole
    ){
        AuthResponse response = authService.switchRole(userId,newRole);
        return ResponseEntity.ok(response);
    }
}
