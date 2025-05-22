package com.example.authentification_test.service;

import com.example.authentification_test.Respository.UserRespository;
import com.example.authentification_test.dto.AuthResponse;
import com.example.authentification_test.dto.LoginRequest;
import com.example.authentification_test.dto.RegisterRequest;
import com.example.authentification_test.entities.Role;
import com.example.authentification_test.entities.User;
import com.example.authentification_test.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRespository userRespository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    //Enregistre un nouvel user en verifiant s'il existe deja

    public AuthResponse registerUser(RegisterRequest request){
        if(userRespository.findByEmail(request.getEmail()).isPresent()){
            throw new IllegalArgumentException("Cet email est deja utilise");
        }

        // verification du role choisi
        Role role = request.getSelectedRole();
        if(role != Role.USER && role != Role.PRODUCER){
            throw new IllegalArgumentException("Role non valide pour l'inscription");
        }

        // creation de l'utilisateur avec role choisi
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .adresse(request.getAdress())
                .enabled(true)
                .currentRole(role)
                .availableRoles(Set.of(role))
                .build();

        userRespository.save(user);

        //Generation du token JWT
        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token,user.getCurrentRole());
    }


    //Authentifie le user existant a pertir de l'email et du mot de passe

    public AuthResponse authenticateUser(LoginRequest request){
        User user = userRespository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Email ou mot de passe incorrect."));
        if(!user.isEnabled()){
            throw new IllegalArgumentException("Ce compte est desactiver.");
        }
        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new IllegalArgumentException("Email ou mot de passe incorrect.");
        }

        String token = jwtUtil.generateToken(user);

        return new AuthResponse(token,user.getCurrentRole());
    }

    //change de role actif d'un utilisateur si autorise

    public  AuthResponse switchRole(Long userId, Role newRole){
        User user = userRespository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable."));
        if(!user.hasRole(newRole)){
            throw new IllegalArgumentException("ce role n'est pas disponible");
        }
        user.setCurrentRole(newRole);
        userRespository.save(user);

        String newToken = jwtUtil.generateToken(user);

        return new AuthResponse(newToken,newRole);
    }
}
