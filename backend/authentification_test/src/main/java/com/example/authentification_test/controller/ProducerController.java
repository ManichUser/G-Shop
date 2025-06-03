package com.example.authentification_test.controller;


import com.example.authentification_test.entities.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Fournisseur",description = "Fonctionnalites reservees aux producteurs")
@RestController
@RequestMapping("/api/producer")
public class ProducerController {
    @GetMapping("/mes-produits")
    @PreAuthorize("hasRole('PRODUCER')")
    public ResponseEntity<?> getMyProducts(@AuthenticationPrincipal User user){
        return ResponseEntity.ok("Bienvenue" +user.getFirstName() + "voici vos produits");
    }
}
