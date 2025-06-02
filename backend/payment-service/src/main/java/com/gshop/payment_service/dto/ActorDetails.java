package com.gshop.payment_service.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;



import lombok.*;

/**
 * DTO représentant les détails d'un acteur (client, fournisseur, vendeur) impliqué dans une transaction.
 * Peut être une source (expéditeur) ou une cible (destinataire) de paiement.
 */

@Data// Génère les getters, setters, toString, equals, hashCode
@NoArgsConstructor // Génère un constructeur sans arguments
@AllArgsConstructor // Génère un constructeur avec tous les arguments
public class ActorDetails {

    // Identifiant unique de l'acteur dans le système appelant (ex: ID client, ID fournisseur)
    @NotBlank(message = "L'ID de l'acteur est obligatoire")
    private String actorId;

    // Montant partiel si l'acteur est impliqué dans une transaction multiple (ex: achat groupé)
    @Positive(message = "Le montant partiel doit être positif")
    private Double partialAmount;

    // Numéro de téléphone pour les paiements Mobile Money
    private String phoneNumber;

    // Email de l'acteur (pour référence ou communication)
    private String email;

    // Nom complet de l'acteur
    private String fullName;

    // Référence à la transaction externe initiale (utile pour les remboursements par carte)
    private String initialExternalTransactionRef;

    // Autres détails spécifiques à l'acteur ou à la méthode de paiement (ex: coordonnées bancaires pour virement)
    private String bankAccountNumber;
    private String bankName;
    private String bankCode;
}
