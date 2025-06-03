package com.gshop.payment_service.dto;

import com.gshop.payment_service.model.enums.PaymentStatusEnum;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO pour la réponse d'un paiement initié.
 * Contient les informations que notre API renvoie au client après avoir reçu une requête de paiement.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {

    private UUID paymentId; // L'ID unique de notre paiement
    private String externalOrderId; // L'ID de commande du client
    private Double amount; // Montant du paiement
    private String currency; // Devise
    private String paymentMethod; // Méthode de paiement
    private PaymentStatusEnum status; // Statut actuel du paiement (ex: PENDING)
    private String externalTransactionId; // <-- CHAMPS AJOUTÉ : ID de la transaction chez le fournisseur externe
    private String message; // Message décrivant le statut ou une instruction (ex: "Veuillez confirmer le paiement sur votre mobile")
    private String redirectUrl; // URL vers laquelle le client doit être redirigé si nécessaire (ex: page de paiement du fournisseur)
    private LocalDateTime createdAt; // Date et heure de création de l'enregistrement du paiement



}
