package com.gshop.payment_service.external;

import com.gshop.payment_service.dto.ActorDetails;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j; // Pour la journalisation (logging)

/**
 * Adaptateur simulant les appels à l'API MTN Mobile Money.
 * Dans une application réelle, cette classe ferait de vraies requêtes HTTP.
 */
@Component
@Slf4j
public class MtnMomoAdapter {

    // Simule un paiement entrant (Client -> Plateforme) via MTN MoMo
    public String initiateIncomingPayment(ActorDetails clientSource, Double amount, String currency, String reference, String description, String callbackUrl) {
        log.info("Simulating MTN MoMo incoming payment for client {} with amount {} {}", clientSource.getPhoneNumber(), amount, currency);
        // Ici, mons ferons un appel HTTP à l'API MTN MoMo.
        String externalRef = "MTN_" + System.currentTimeMillis() + "_" + reference;
        log.info("MTN MoMo incoming payment initiated. External ref: {}", externalRef);
        return externalRef; // Retourne la référence de transaction générée par l'API externe
    }

    // Simule un paiement sortant (Plateforme -> Fournisseur/Client) via MTN MoMo
    public String initiateOutgoingPayment(ActorDetails targetActor, Double amount, String currency, String reference, String description, String callbackUrl) {
        log.info("Simulating MTN MoMo outgoing payment to actor {} with amount {} {}", targetActor.getPhoneNumber(), amount, currency);
        // Ici, nous ferons un appel HTTP à l'API de décaissement de MTN MoMo.
        String externalRef = "MTN_OUT_" + System.currentTimeMillis() + "_" + reference;
        log.info("MTN MoMo outgoing payment initiated. External ref: {}", externalRef);
        return externalRef; // Retourne la référence de transaction générée par l'API externe
    }

    // Simule un remboursement (Plateforme -> Client) via MTN MoMo
    public String initiateRefund(ActorDetails clientTarget, Double amount, String currency, String originalExternalTransactionRef, String reference, String description, String callbackUrl) {
        log.info("Simulating MTN MoMo refund for client {} with amount {} {} for original transaction {}", clientTarget.getPhoneNumber(), amount, currency, originalExternalTransactionRef);
        // Ici, nous ferons un appel HTTP à l'API de remboursement de MTN MoMo.
        String externalRef = "MTN_REF_" + System.currentTimeMillis() + "_" + reference;
        log.info("MTN MoMo refund initiated. External ref: {}", externalRef);
        return externalRef; // Retourne la référence de transaction générée par l'API externe
    }

    // Simule la vérification du statut d'une transaction externe
    public String checkTransactionStatus(String externalTransactionRef) {
        log.info("Simulating MTN MoMo status check for external ref: {}", externalTransactionRef);
        // Dans une vraie application, vous appelleriez l'API de vérification de statut.
        if (System.currentTimeMillis() % 2 == 0) {
            return "SUCCESS";
        } else {
            return "PENDING";
        }
    }
}
