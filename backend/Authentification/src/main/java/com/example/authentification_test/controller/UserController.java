package com.example.authentification_test.controller;

import com.example.authentification_test.Respository.ProducerDetailsRespository;
import com.example.authentification_test.Respository.UserRespository;
import com.example.authentification_test.dto.ProducerRegisterRequest;
import com.example.authentification_test.entities.Role;
import com.example.authentification_test.entities.User;
import com.example.authentification_test.security.JwtUtil;
import com.example.authentification_test.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "Utilisateur", description = "Gestion des roles, profils et actions utilisateur")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRespository userRespository;
    private final ProducerDetailsRespository producerDetailsRespository;
    private final JwtUtil jwtUtil;
    private final AuthService authService;

    // Récupère les informations du user connecté
    @GetMapping("/me")
    public ResponseEntity<?> me(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(user);
    }

    // Change le rôle actif d’un utilisateur connecté
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/switch-role")
    public ResponseEntity<?> switchRole(@RequestParam String role, @AuthenticationPrincipal User user) {
        try {
            Role newRole = Role.valueOf(role.toUpperCase());
            if (!user.getAvailableRoles().contains(newRole)) {
                return ResponseEntity.badRequest().body("Vous n'avez pas accès à ce rôle.");
            }

            user.setCurrentRole(newRole);
            userRespository.save(user);

            String newToken = jwtUtil.generateToken(user);

            return ResponseEntity.ok(Map.of(
                    "accessToken", newToken,
                    "currentRole", user.getCurrentRole(),
                    "tokenType", "Bearer"
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Rôle non valide : " + role);
        }
    }

    // Permet à un utilisateur USER de devenir fournisseur (PRODUCER)
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/devenir-fournisseur")
    public ResponseEntity<?> devenirFournisseur(
            @RequestBody ProducerRegisterRequest request,
            @AuthenticationPrincipal User user
    ) {
        try {
            authService.ConvertToProducer(user, request);
            String token = jwtUtil.generateToken(user);
            return ResponseEntity.ok(Map.of(
                    "accessToken", token,
                    "currentRole", "PRODUCER",
                    "tokenType", "Bearer"
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Renvoie le rôle actuel et tous les rôles disponibles du user
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/roles")
    public ResponseEntity<?> getRoles(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(Map.of(
                "currentRole", user.getCurrentRole(),
                "availableRoles", user.getAvailableRoles()
        ));
    }

    // Vérifie si l'utilisateur est fournisseur
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/is-producer")
    public ResponseEntity<?> isProducer(@AuthenticationPrincipal User user) {
        boolean isProducer = producerDetailsRespository.findByUser(user).isPresent();
        return ResponseEntity.ok(Map.of("isProducer", isProducer));
    }
}
