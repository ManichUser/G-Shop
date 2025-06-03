package com.example.panier_service.service;


import com.example.panier_service.dto.CommandeRequestDTO;
import com.example.panier_service.dto.CommandeResponseDTO;
import com.example.panier_service.dto.ProduitDTO;
import com.example.panier_service.mapper.CommandeMapper;
import com.example.panier_service.model.Commande;
import com.example.panier_service.model.CommandeStatus;
import com.example.panier_service.repository.CommandeRepository;
import lombok.RequiredArgsConstructor;
import com.example.panier_service.client.ProduitClient;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository commandeRepository;
    private final ProduitClient produitClient;

public List<CommandeResponseDTO> getAllCommandes() {
    System.out.println(">>> [DEBUG] Service : récupération de toutes les commandes");

    List<Commande> commandes = commandeRepository.findAll();
    System.out.println(">>> [DEBUG] Nombre de commandes trouvées : " + commandes.size());

    List<CommandeResponseDTO> dtos = commandes.stream()
        .map(this::mapToResponseDTO)
        .toList();

    System.out.println(">>> [DEBUG] Conversion en DTO terminée");

    return dtos;
}
@Override
public CommandeResponseDTO createCommande(CommandeRequestDTO request) {
    System.out.println(">>> [DEBUG] Création d'une commande reçue : " + request);

    // Suppression de la récupération du produit et du seuil groupage
    // Suppression de la vérification du seuil

    // Création de l'entité
    Commande commande = CommandeMapper.toEntity(request);
    System.out.println(">>> [DEBUG] Entité Commande créée : " + commande);

    // Sauvegarde
    Commande saved = commandeRepository.save(commande);
    System.out.println(">>> [DEBUG] Commande sauvegardée : " + saved);

    // Conversion DTO réponse
    CommandeResponseDTO response = CommandeMapper.toDto(saved);
    System.out.println(">>> [DEBUG] Réponse retournée : " + response);

    return response;
}



    private CommandeResponseDTO mapToResponseDTO(Commande commande) {
        return CommandeResponseDTO.builder()
                .id(commande.getId())
                .userId(commande.getUserId())
                .productId(commande.getProductId())
                .quantity(commande.getQuantity())
                .createdAt(commande.getCreatedAt())
                .build();
    }



 @Override
public List<CommandeResponseDTO> getCommandesByUserId(String userId) {
    List<Commande> commandes = commandeRepository.findByUserId(userId);

    return commandes.stream()
        .map(commande -> {
            CommandeResponseDTO dto = CommandeMapper.toDto(commande);
            // On ne récupère plus le prix via Feign
            // dto.setPrix(...) est supprimé
            return dto;
        })
        .collect(Collectors.toList());
}


    @Override
    public List<CommandeResponseDTO> getCommandesByProductId(String productId) {
        return commandeRepository.findByProductId(productId)
                .stream()
                .map(CommandeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCommande(String id) {
        if (!commandeRepository.existsById(id)) {
            throw new RuntimeException("Commande non trouvée pour suppression : " + id);
        }
        commandeRepository.deleteById(id);
    }

    public CommandeResponseDTO updateStatus(String commandeId, CommandeStatus newStatus) {
        // Recherche de la commande par son ID
        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée pour mise à jour du statut : " + commandeId));

        // Mise à jour du statut
        commande.setStatus(newStatus);

        // Sauvegarde de la commande modifiée
        Commande updated = commandeRepository.save(commande);

        // Conversion en DTO de réponse
        return CommandeMapper.toDto(updated);
    }

  




}

