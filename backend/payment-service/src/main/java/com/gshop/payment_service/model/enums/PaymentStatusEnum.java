package com.gshop.payment_service.model.enums;

/**
 * Énumération des statuts possibles pour un paiement.
 */
public enum PaymentStatusEnum {

    PENDING,// Le paiement est en attente de confirmation du fournisseur externe.

    SUCCESS,// Le paiement a été traité avec succès.

    FAILED,// Le paiement a échoué.

    REFUNDED,// Le paiement a été remboursé.

    CANCELLED// Le paiement a été annulé avant traitement.
}
