// src/main/java/com/gshop/payment_service/enums/PaymentMethod.java
package com.gshop.payment_service.model.enums;

/**
 * Énumération des méthodes de paiement supportées par le microservice.
 */
public enum PaymentMethod {
    // Paiement via Orange Money
    ORANGE_MONEY,
    // Paiement via MTN Mobile Money
    MTN_MOMO,
    // Virement bancaire (pour les décaissements vers les fournisseurs/vendeurs si applicable)
    BANK_TRANSFER
    // La carte bancaire est exclue pour le moment
    // CARD
}
