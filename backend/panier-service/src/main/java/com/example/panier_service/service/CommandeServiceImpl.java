package com.example.panier_service.service;

import com.example.panier_service.client.ProduitClient;
import com.example.panier_service.dto.CommandeRequestDTO;
import com.example.panier_service.dto.CommandeResponseDTO;
import com.example.panier_service.dto.ProduitDTO;
import com.example.panier_service.mapper.CommandeMapper;
import com.example.panier_service.model.Commande;
import com.example.panier_service.model.CommandeStatus;
import com.example.panier_service.repository.CommandeRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
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

        ProduitDTO produit;
        try {
            // Pas besoin de parse Long.parseLong(request.getProductId()) car request.getProductId() est déjà un Long
            produit = produitClient.getProduitById(request.getProductId());
            if (produit == null) {
                throw new RuntimeException("Produit avec l'ID " + request.getProductId() + " introuvable dans le service Produit.");
            }
            System.out.println(">>> [DEBUG] Produit récupéré du service Produit : " + produit.getNom() + " (Seuil: " + produit.getSeuilGroupage() + ")");
        } catch (feign.FeignException.NotFound e) {
            System.err.println(">>> [ERROR] Produit non trouvé via Feign pour ID " + request.getProductId() + " : " + e.getMessage());
            throw new RuntimeException("Produit avec l'ID " + request.getProductId() + " introuvable.", e);
        } catch (Exception e) {
            System.err.println(">>> [ERROR] Erreur de communication avec le service Produit lors de la récupération de l'ID " + request.getProductId() + " : " + e.getMessage());
            throw new RuntimeException("Le service Produit est actuellement indisponible ou a rencontré une erreur.", e);
        }

        int seuilGroupage = produit.getSeuilGroupage();

        // Note : Si votre CommandeRepository recherche par productId String, vous devrez convertir Long en String ici
        // ou modifier findByProductId pour accepter un Long si votre champ productId dans Commande est un Long.
        // Si Commande.productId est toujours String, alors il faudra : String.valueOf(request.getProductId())
        // Pour l'instant, je pars du principe que Commande.productId est un String et que findByProductId accepte String.
        // Si Commande.productId DOIT ÊTRE UN LONG, il faut le modifier dans Commande.java ET CommandeRepository.java.
        int quantiteDejaCommandee = commandeRepository.findByProductId(request.getProductId()) // <-- Conversion de Long en String
                                           .stream()
                                           .mapToInt(Commande::getQuantity)
                                           .sum();
        System.out.println(">>> [DEBUG] Quantité déjà commandée pour le produit " + request.getProductId() + " : " + quantiteDejaCommandee);

        int totalQuantiteApresCommande = quantiteDejaCommandee + request.getQuantity();
        System.out.println(">>> [DEBUG] Total quantité après la nouvelle commande : " + totalQuantiteApresCommande);

        if (totalQuantiteApresCommande > seuilGroupage) {
            System.out.println(">>> [WARN] Seuil de groupage dépassé pour le produit " + produit.getNom());
            throw new RuntimeException(
                "Le seuil de groupage de " + seuilGroupage + " pour le produit '" + produit.getNom() +
                "' serait dépassé. Quantité déjà commandée: " + quantiteDejaCommandee +
                ", Nouvelle quantité demandée: " + request.getQuantity() +
                ", Total projeté: " + totalQuantiteApresCommande
            );
        }

        Commande commande = CommandeMapper.toEntity(request); // Le mapper devra gérer le Long productId
        commande.setCreatedAt(LocalDateTime.now().toInstant(ZoneOffset.UTC));
        commande.setStatus(CommandeStatus.CREATED);
        System.out.println(">>> [DEBUG] Entité Commande créée : " + commande);

        Commande saved = commandeRepository.save(commande);
        System.out.println(">>> [DEBUG] Commande sauvegardée : " + saved);

        CommandeResponseDTO response = CommandeMapper.toDto(saved);
        response.setPrix(produit.getPrix()); // <-- N'oubliez pas d'ajouter le prix récupéré du produit
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
            .status(commande.getStatus())
            // Note: le prix n'est pas dans l'entité Commande, il ne peut pas être mapé directement ici.
            // Il doit être ajouté spécifiquement après l'appel au ProduitClient dans createCommande.
            .build();
    }

    @Override
    public List<CommandeResponseDTO> getCommandesByUserId(String userId) {
        List<Commande> commandes = commandeRepository.findByUserId(userId);
        return commandes.stream()
            .map(CommandeMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<CommandeResponseDTO> getCommandesByProductId(Long productId) {
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
        Commande commande = commandeRepository.findById(commandeId)
            .orElseThrow(() -> new RuntimeException("Commande non trouvée pour mise à jour du statut : " + commandeId));

        commande.setStatus(newStatus);

        Commande updated = commandeRepository.save(commande);

        return CommandeMapper.toDto(updated);
    }
}