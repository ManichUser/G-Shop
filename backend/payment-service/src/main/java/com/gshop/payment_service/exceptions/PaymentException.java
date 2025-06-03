// src/main/java/com/gshop/payment_service/exceptions/PaymentException.java
package com.gshop.payment_service.exceptions;

/**
 * Exception personnalisée pour les erreurs spécifiques au domaine du paiement.
 * Cela nous permet de renvoyer des messages d'erreur clairs et de gérer des cas métier spécifiques.
 */
public class PaymentException extends RuntimeException {

    public PaymentException(String message) {
        super(message); // Appelle le constructeur de RuntimeException avec le message
    }

    public PaymentException(String message, Throwable cause) {
        super(message, cause); // Appelle le constructeur de RuntimeException avec message et cause
    }
}
