// src/main/java/com/gshop/payment_service/service/OrangeMoneyService.java
package com.gshop.payment_service.service;

import com.gshop.payment_service.dto.PaymentRequest;
import com.gshop.payment_service.exceptions.PaymentException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono; // Pour la programmation réactive
import lombok.extern.slf4j.Slf4j; // Pour la journalisation (logging)

/**
 * Service dédié à l'intégration avec l'API Orange Money.
 * Simule les appels pour les paiements entrants, sortants et les remboursements.
 */
@Service // Indique que c'est un composant de service Spring
@Slf4j // Active le logger Lombok pour cette classe
public class OrangeMoneyService {

    // Injecte l'URL de l'API Orange Money depuis application.yml
    @Value("${orange-api.url}")
    private String orangeApiUrl;

    /**
     * Simule l'initiation d'un paiement entrant (Client -> Plateforme) via Orange Money.
     * Dans une application réelle, cela ferait un appel WebClient à l'API Orange Money.
     *
     * @param request La requête de paiement contenant les détails du payeur.
     * @return Mono<String> contenant la référence de transaction externe générée par Orange Money.
     */
    public Mono<String> initiateIncomingPayment(PaymentRequest request) {
        log.info("Simulating Orange Money incoming payment to {} for amount {} {}",
                request.getPayerIdentifier(), request.getAmount(), request.getCurrency());
        log.debug("Orange API URL: {}", orangeApiUrl);

        // Simulation d'un appel API externe.
        // Dans un cas réel, vous utiliseriez WebClient ici:
        // return WebClient.builder().baseUrl(orangeApiUrl).build()
        //      .post()
        //      .bodyValue(Map.of("email", request.getEmail(), "currency", request.getCurrency(), ...))
        //      .retrieve()
        //      .bodyToMono(OrangeApiResponse.class)
        //      .map(response -> response.getExternalTransactionId())
        //      .onErrorResume(e -> Mono.error(new PaymentException("Failed to initiate OM payment", e)));

        // Génère une référence externe fictive pour la simulation
        String externalRef = "OM_INC_" + System.currentTimeMillis() + "_" + request.getExternalOrderId();
        log.info("Orange Money incoming payment simulated. External ref: {}", externalRef);
        return Mono.just(externalRef); // Retourne un Mono contenant la référence simulée
    }

    /**
     * Simule l'initiation d'un paiement sortant (Plateforme -> Acteur Unique) via Orange Money.
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
        log.info("Simulating Orange Money outgoing payment to {} for amount {} {}",
                payeeIdentifier, amount, currency);
        log.debug("Orange API URL: {}", orangeApiUrl);

        String externalRef = "OM_OUT_" + System.currentTimeMillis() + "_" + externalOrderId;
        log.info("Orange Money outgoing payment simulated. External ref: {}", externalRef);
        return Mono.just(externalRef);
    }

    /**
     * Simule l'initiation d'un remboursement (Plateforme -> Client) via Orange Money.
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
        log.info("Simulating Orange Money refund to {} for amount {} {} (original ref: {})",
                payeeIdentifier, amount, currency, originalExternalTransactionRef);
        log.debug("Orange API URL: {}", orangeApiUrl);

        String externalRef = "OM_REF_" + System.currentTimeMillis() + "_" + externalOrderId;
        log.info("Orange Money refund simulated. External ref: {}", externalRef);
        return Mono.just(externalRef);
    }

    /**
     * Simule la vérification du statut d'une transaction Orange Money.
     *
     * @param externalTransactionId L'ID de transaction externe à vérifier.
     * @return Mono<String> contenant le statut (ex: "SUCCESS", "PENDING", "FAILED").
     */
    public Mono<String> checkTransactionStatus(String externalTransactionId) {
        log.info("Simulating Orange Money status check for external ref: {}", externalTransactionId);
        // Pour la simulation, on alterne entre SUCCESS et PENDING
        if (System.currentTimeMillis() % 2 == 0) {
            return Mono.just("SUCCESS");
        } else {
            return Mono.just("PENDING");
        }
    }
}
