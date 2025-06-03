package com.example.panier_service.controller;


import com.example.panier_service.dto.CommandeRequestDTO;
import com.example.panier_service.dto.CommandeResponseDTO;
import com.example.panier_service.service.CommandeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.example.panier_service.model.CommandeStatus;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
@RequiredArgsConstructor
public class CommandeController {

    private final CommandeService commandeService;

    @PostMapping
    public CommandeResponseDTO createCommande(@RequestBody @Valid CommandeRequestDTO request) {
        return commandeService.createCommande(request);
    }

    @GetMapping
    public List<CommandeResponseDTO> getAllCommandes() {
        System.out.println(">>> [DEBUG] Requête GET reçue sur /api/commandes");

        List<CommandeResponseDTO> commandes = commandeService.getAllCommandes();

        System.out.println(">>> [DEBUG] Nombre de commandes récupérées : " + commandes.size());

        // Afficher le détail de chaque commande (facultatif)
        commandes.forEach(c -> System.out.println(">>> [DEBUG] Commande : " + c));

        return commandes;
    }

    @GetMapping("/user/{userId}")
    public List<CommandeResponseDTO> getByUserId(@PathVariable String userId) {
        return commandeService.getCommandesByUserId(userId);
    }

    @GetMapping("/product/{productId}")
    public List<CommandeResponseDTO> getByProductId(@PathVariable Long productId) {
        return commandeService.getCommandesByProductId(productId);
    }

    @DeleteMapping("/{id}")
    public void deleteCommande(@PathVariable String id) {
        commandeService.deleteCommande(id);
    }
    
    @PatchMapping("/{id}/status")
    public CommandeResponseDTO updateStatus(@PathVariable String id, @RequestParam CommandeStatus status) {
        return commandeService.updateStatus(id, status);
    }

  
   

}
