package com.example.authentification_test.dto;

import com.example.authentification_test.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String accessToken;
    private Role currentRole;
    //private String tokenType="Bearer";
}
