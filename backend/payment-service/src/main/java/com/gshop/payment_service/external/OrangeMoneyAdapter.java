package com.gshop.payment_service.external;

import com.gshop.payment_service.dto.ActorDetails;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j; // Pour la journalisation (logging)

/**
 * Adaptateur simulant les appels à l'API Orange Money.
 * Dans une application réelle, cette classe ferait de vraies requêtes HTTP.
 */
@Component
@Slf4j
public class OrangeMoneyAdapter {

    // Simule un paiement entrant (Client -> Plateforme) via Orange Money
    public String initiateIncomingPayment(ActorDetails clientSource, Double amount, String currency, String reference, String description, String callbackUrl) {
        log.info("Simulating Orange Money incoming payment for client {} with amount {} {}", clientSource.getPhoneNumber(), amount, currency);
        String externalRef = "OM_" + System.currentTimeMillis() + "_" + reference;
        log.info("Orange Money incoming payment initiated. External ref: {}", externalRef);
        return externalRef; // Retourne la référence de transaction générée par l'API externe
    }

    // Simule un paiement sortant (Plateforme -> Fournisseur/Client) via Orange
    public String initiateOutgoingPayment(ActorDetails targetActor, Double amount, String currency, String reference, String description, String callbackUrl) {
        log.info("Simulating Orange Money outgoing payment to actor {} with amount {} {}", targetActor.getPhoneNumber(), amount, currency);
        // Ici, nous ferons un appel HTTP à l'API de décaissement d'Orange Money.
        String externalRef = "OM_OUT_" + System.currentTimeMillis() + "_" + reference;
        log.info("Orange Money outgoing payment initiated. External ref: {}", externalRef);
        return externalRef; // Retourne la référence de transaction générée par l'API externe
    }

    // Simule un remboursement (Plateforme -> Client) via Orange Money
    public String initiateRefund(ActorDetails clientTarget, Double amount, String currency, String originalExternalTransactionRef, String reference, String description, String callbackUrl) {
        log.info("Simulating Orange Money refund for client {} with amount {} {} for original transaction {}", clientTarget.getPhoneNumber(), amount, currency, originalExternalTransactionRef);
        // Ici, vous feriez un appel HTTP à l'API de remboursement d'Orange Money.
        String externalRef = "OM_REF_" + System.currentTimeMillis() + "_" + reference;
        log.info("Orange Money refund initiated. External ref: {}", externalRef);
        return externalRef; // Retourne la référence de transaction générée par l'API externe
    }

    // Simule la vérification du statut d'une transaction externe (utilisé pour le polling si pas de callback)
    public String checkTransactionStatus(String externalTransactionRef) {
        log.info("Simulating Orange Money status check for external ref: {}", externalTransactionRef);
        if (System.currentTimeMillis() % 2 == 0) {
            return "SUCCESS";
        } else {
            return "PENDING"; // Ou FAILED après un certain temps
        }
    }
}
