package com.gshop.payment_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data; // Pour les getters, setters, etc.

/**
 * DTO (Data Transfer Object) pour la requête d'initiation d'un paiement.
 * C'est le contrat pour les données que notre API attend en entrée.
 */
@Data // Génère automatiquement les getters, setters, toString, equals, hashCode (Lombok)
public class PaymentRequest {

    @NotBlank(message = "L'ID de commande externe ne peut pas être vide")
    private String externalOrderId; // ID de la commande chez le client (ex: ID du panier)

    @NotNull(message = "Le montant ne peut pas être nul")
    @Positive(message = "Le montant doit être un nombre positif")
    private Double amount; // Montant du paiement

    @NotBlank(message = "La devise ne peut pas être vide")
    private String currency; // Devise (ex: XAF)

    @NotBlank(message = "La méthode de paiement ne peut pas être vide")
    private String paymentMethod; // Méthode de paiement (ex: ORANGE_MONEY, MTN_MOMO)

    @NotBlank(message = "L'identifiant du payeur ne peut pas être vide")
    private String payerIdentifier; // Numéro de téléphone ou autre identifiant du payeur

    private String description; // Description optionnelle du paiement

    // URL où le fournisseur de paiement (ou notre service) doit envoyer les notifications de statut.
    @NotBlank(message = "L'URL de callback ne peut pas être vide")
    private String callbackUrl;
}
