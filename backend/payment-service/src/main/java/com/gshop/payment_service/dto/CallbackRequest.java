package com.gshop.payment_service.dto;

import com.gshop.payment_service.model.enums.PaymentStatusEnum; // Import de l'énumération de statut
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * DTO pour les requêtes de callback (webhooks) reçues des fournisseurs de paiement.
 * Les champs réels dépendront fortement de l'API spécifique de chaque fournisseur.
 * Ceci est un exemple générique.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CallbackRequest {

    private String externalTransactionId; // L'ID de la transaction chez le fournisseur (Orange/MTN)
    private PaymentStatusEnum status; // Le statut final de la transaction selon le fournisseur (SUCCESS, FAILED)
    private String message; // Un message optionnel du fournisseur

}
