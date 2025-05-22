package com.example.authentification_test.dto;

import com.example.authentification_test.entities.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String adress;

    //le role choisi par le user a l'inscription
    private Role selectedRole;
}
