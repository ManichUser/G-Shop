// src/main/java/com/gshop/payment_service/service/MTNMoMoService.java
package com.gshop.payment_service.service;

import com.gshop.payment_service.dto.PaymentRequest;
import com.gshop.payment_service.exceptions.PaymentException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono; // Pour la programmation réactive
import lombok.extern.slf4j.Slf4j; // Pour la journalisation (logging)

/**
 * Service dédié à l'intégration avec l'API MTN Mobile Money.
 * Simule les appels pour les paiements entrants, sortants et les remboursements.
 */
@Service // Indique que c'est un composant de service Spring
@Slf4j // Active le logger Lombok pour cette classe
public class MTNMoMoService {

    // Injecte l'URL de l'API MTN MoMo depuis application.yml
    @Value("${mtn-api.url}")
    private String mtnApiUrl;

    /**
     * Simule l'initiation d'un paiement entrant (Client -> Plateforme) via MTN MoMo.
     *
     * @param request La requête de paiement contenant les détails du payeur.
     * @return Mono<String> contenant la référence de transaction externe générée par MTN MoMo.
     */
    public Mono<String> initiateIncomingPayment(PaymentRequest request) {
        log.info("Simulating MTN MoMo incoming payment to {} for amount {} {}",
                request.getPayerIdentifier(), request.getAmount(), request.getCurrency());
        log.debug("MTN API URL: {}", mtnApiUrl);

        String externalRef = "MTN_INC_" + System.currentTimeMillis() + "_" + request.getExternalOrderId();
        log.info("MTN MoMo incoming payment simulated. External ref: {}", externalRef);
        return Mono.just(externalRef);
    }

    /**
     * Simule l'initiation d'un paiement sortant (Plateforme -> Acteur Unique) via MTN MoMo.
     *
     * @param payeeIdentifier L'identifiant du destinataire (ex: numéro de téléphone).
     * @param amount Le montant à décaisser.
     * @param currency La devise.
     * @param externalOrderId La référence de commande externe.
     * @param description La description du paiement.
     * @param callbackUrl L'URL de callback.
     * @return Mono<String> contenant la référence de transaction externe.
     */
    public Mono<String> initiateOutgoingPayment(String payeeIdentifier, Double amount, String currency, String externalOrderId, String description, String callbackUrl) {
        log.info("Simulating MTN MoMo outgoing payment to {} for amount {} {}",
                payeeIdentifier, amount, currency);
        log.debug("MTN API URL: {}", mtnApiUrl);

        String externalRef = "MTN_OUT_" + System.currentTimeMillis() + "_" + externalOrderId;
        log.info("MTN MoMo outgoing payment simulated. External ref: {}", externalRef);
        return Mono.just(externalRef);
    }

    /**
     * Simule l'initiation d'un remboursement (Plateforme -> Client) via MTN MoMo.
     *
     * @param payeeIdentifier L'identifiant du client à rembourser.
     * @param amount Le montant à rembourser.
     * @param currency La devise.
     * @param originalExternalTransactionRef La référence de la transaction initiale (si applicable).
     * @param externalOrderId La référence de commande externe.
     * @param description La description du remboursement.
     * @param callbackUrl L'URL de callback.
     * @return Mono<String> contenant la référence de transaction externe du remboursement.
     */
    public Mono<String> initiateRefund(String payeeIdentifier, Double amount, String currency, String originalExternalTransactionRef, String externalOrderId, String description, String callbackUrl) {
        log.info("Simulating MTN MoMo refund to {} for amount {} {} (original ref: {})",
                payeeIdentifier, amount, currency, originalExternalTransactionRef);
        log.debug("MTN API URL: {}", mtnApiUrl);

        String externalRef = "MTN_REF_" + System.currentTimeMillis() + "_" + externalOrderId;
        log.info("MTN MoMo refund simulated. External ref: {}", externalRef);
        return Mono.just(externalRef);
    }

    /**
     * Simule la vérification du statut d'une transaction MTN MoMo.
     *
     * @param externalTransactionId L'ID de transaction externe à vérifier.
     * @return Mono<String> contenant le statut (ex: "SUCCESS", "PENDING", "FAILED").
     */
    public Mono<String> checkTransactionStatus(String externalTransactionId) {
        log.info("Simulating MTN MoMo status check for external ref: {}", externalTransactionId);
        if (System.currentTimeMillis() % 2 == 0) {
            return Mono.just("SUCCESS");
        } else {
            return Mono.just("PENDING");
        }
    }
}
