package com.example.authentification_test.controller;


import com.example.authentification_test.Respository.UserRespository;
import com.example.authentification_test.entities.Role;
import com.example.authentification_test.entities.User;
import com.example.authentification_test.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRespository userRespository;
    private final JwtUtil jwtUtil;
    @GetMapping("/me")
    public ResponseEntity<?>me(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/switch-role")
    public ResponseEntity<?>
    switchRole(@RequestParam String role, @AuthenticationPrincipal User user){
        try {
            Role newRole = Role.valueOf(role.toUpperCase());
            if(!user.getAvailableRoles().contains(newRole)){
                return ResponseEntity.badRequest().body("Vous n'avez pas acces");
            }
            user.setCurrentRole(newRole);
            userRespository.save(user);

            //generer un nouveau token avce le role mise a jour
            String newToken = jwtUtil.generateToken(user);

            return ResponseEntity.ok().body(Map.of("accessToken",newToken, "currentRole",user.getCurrentRole(),"tokenType","Bearer"));
        }catch (IllegalArgumentException e)
        {
            return ResponseEntity.badRequest().body("Role non valide:" + role);
        }
    }
}
