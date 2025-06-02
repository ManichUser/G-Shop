package com.gshop.payment_service.controller;

import com.gshop.payment_service.dto.CallbackRequest;
import com.gshop.payment_service.exceptions.PaymentException;
import com.gshop.payment_service.service.PaymentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono; // Pour la programmation réactive

/**
  Contrôleur REST pour la réception des callbacks/webhooks des fournisseurs de paiement externes.
 */
@RestController
@RequestMapping("/callbacks")
@RequiredArgsConstructor
@Slf4j
public class CallbackController {

    private final PaymentService paymentService;

    /**
      Endpoint pour recevoir les callbacks d'Orange Money.
      Les données réelles du corps de la requête dépendront de l'API Orange Money.
      Nous utilisons un DTO générique `CallbackRequest` pour la simulation.

     */
    @PostMapping("/orange-money")
    public Mono<ResponseEntity<Object>> handleOrangeMoneyCallback(@RequestBody CallbackRequest callbackRequest) {
        log.info("Received Orange Money callback for external transaction ID: {}", callbackRequest.getExternalTransactionId());
        // Dans une application réelle, vous feriez une validation de signature ici
        // Mono.fromCallable(() -> SecurityUtil.validateOrangeMoneySignature(requestHeaders, requestBody))
        //     .flatMap(isValid -> { if (!isValid) return Mono.error(new PaymentException("Invalid signature")); return Mono.empty(); })
        return paymentService.handleCallback(callbackRequest, "ORANGE_MONEY")
                .then(Mono.just(new ResponseEntity<>(HttpStatus.OK))) // Renvoie 200 OK après traitement
                .onErrorResume(PaymentException.class, e -> {
                    log.error("PaymentException during Orange Money callback {}: {}", callbackRequest.getExternalTransactionId(), e.getMessage());
                    return Mono.just(new ResponseEntity<>(HttpStatus.BAD_REQUEST)); // Mauvaise requête si la transaction est introuvable ou les données sont invalides
                })
                .onErrorResume(Exception.class, e -> {
                    log.error("Unexpected error during Orange Money callback {}: {}", callbackRequest.getExternalTransactionId(), e.getMessage(), e);
                    return Mono.just(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)); // Erreur interne du serveur
                });
    }

    /**
     Endpoint pour recevoir les callbacks de MTN Mobile Money.
     Similaire à Orange Money, les données réelles dépendront de l'API MTN MoMo.

     */
    @PostMapping("/mtn-momo")
    public Mono<ResponseEntity<Object>> handleMtnMomoCallback(@RequestBody CallbackRequest callbackRequest) {
        log.info("Received MTN MoMo callback for external transaction ID: {}", callbackRequest.getExternalTransactionId());
        // Dans une application réelle, vous feriez une validation de signature ici
        return paymentService.handleCallback(callbackRequest, "MTN_MOMO")
                .then(Mono.just(new ResponseEntity<>(HttpStatus.OK)))
                .onErrorResume(PaymentException.class, e -> {
                    log.error("PaymentException during MTN MoMo callback {}: {}", callbackRequest.getExternalTransactionId(), e.getMessage());
                    return Mono.just(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
                })
                .onErrorResume(Exception.class, e -> {
                    log.error("Unexpected error during MTN MoMo callback {}: {}", callbackRequest.getExternalTransactionId(), e.getMessage(), e);
                    return Mono.just(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
                });
    }
}
