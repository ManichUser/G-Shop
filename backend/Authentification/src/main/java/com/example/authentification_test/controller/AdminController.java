package com.example.authentification_test.controller;

import com.example.authentification_test.Respository.AuditLogRespository;
import com.example.authentification_test.Respository.UserRespository;
import com.example.authentification_test.entities.AuditLog;
import com.example.authentification_test.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserRespository userRepository;
    private final AuditLogRespository auditLogRepository;

    @PutMapping("/disable-user/{id}")
    public ResponseEntity<?> disableUser(@PathVariable Long id, @AuthenticationPrincipal User admin) {
        return updateStatus(id, false, "DISABLE_USER", admin);
    }

    @PutMapping("/enable-user/{id}")
    public ResponseEntity<?> enableUser(@PathVariable Long id, @AuthenticationPrincipal User admin) {
        return updateStatus(id, true, "ENABLE_USER", admin);
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id, @AuthenticationPrincipal User admin) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) return ResponseEntity.badRequest().body("Utilisateur introuvable.");

        userRepository.deleteById(id);

        auditLogRepository.save(AuditLog.builder()
                .action("DELETE_USER")
                .performedBy(admin.getEmail())
                .targetUser(user.get().getEmail())
                .timestamp(LocalDateTime.now())
                .details("Suppression par admin.")
                .build());

        return ResponseEntity.ok("Utilisateur supprimé.");
    }

    private ResponseEntity<?> updateStatus(Long id, boolean status, String action, User admin) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) return ResponseEntity.badRequest().body("Utilisateur introuvable.");

        User user = optionalUser.get();
        user.setEnabled(status);
        userRepository.save(user);

        auditLogRepository.save(AuditLog.builder()
                .action(action)
                .performedBy(admin.getEmail())
                .targetUser(user.getEmail())
                .timestamp(LocalDateTime.now())
                .details("Action d'administration")
                .build());

        return ResponseEntity.ok("Utilisateur " + (status ? "activé" : "désactivé") + ".");
    }
}