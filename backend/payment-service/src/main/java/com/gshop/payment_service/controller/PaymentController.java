// src/main/java/com/gshop/payment_service/controller/PaymentController.java
package com.gshop.payment_service.controller;

import com.gshop.payment_service.dto.PaymentRequest;
import com.gshop.payment_service.dto.PaymentResponse;
import com.gshop.payment_service.exceptions.PaymentException;
import com.gshop.payment_service.service.PaymentService;

import jakarta.validation.Valid; // Pour la validation des DTOs
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono; // Pour la programmation réactive

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Contrôleur REST pour la gestion des paiements.
 * Expose les endpoints pour initier et vérifier le statut des paiements.
 * Utilise la programmation réactive (Mono) pour toutes les opérations.
 */
@RestController // Indique que c'est un contrôleur REST
@RequestMapping("/payments") // Définit le chemin de base pour tous les endpoints de ce contrôleur
@RequiredArgsConstructor // Génère un constructeur avec les champs finaux/non null (Lombok)
@Slf4j // Active le logger Lombok pour cette classe
public class PaymentController {

    private final PaymentService paymentService;

    /**
     Endpoint pour initier un nouveau paiement.
     Reçoit un PaymentRequest et renvoie un Mono<PaymentResponse>.
     */
    @PostMapping("/initiate")
    public Mono<ResponseEntity<PaymentResponse>> initiatePayment(@Valid @RequestBody PaymentRequest request) {
        log.info("Received request to initiate payment for external order ID: {}", request.getExternalOrderId());
        return paymentService.initiatePayment(request)
                .map(paymentResponse -> new ResponseEntity<>(paymentResponse, HttpStatus.CREATED))
                .onErrorResume(PaymentException.class, e -> {
                    log.error("PaymentException during initiation for external order ID {}: {}", request.getExternalOrderId(), e.getMessage());
                    return Mono.just(ResponseEntity.badRequest().build());
                })
                .onErrorResume(Exception.class, e -> {
                    log.error("Unexpected error during payment initiation for external order ID {}: {}", request.getExternalOrderId(), e.getMessage(), e);
                    return Mono.just(ResponseEntity.internalServerError().build());
                });
    }

    /**
     * Endpoint pour récupérer le statut d'un paiement par son ID interne.
     */
    @GetMapping("/{paymentId}/status")
    public Mono<ResponseEntity<PaymentResponse>> getPaymentStatus(@PathVariable UUID paymentId) {
        log.info("Received request to get status for payment ID: {}", paymentId);
        return paymentService.getPaymentStatus(paymentId)
                .map(paymentResponse -> ResponseEntity.ok(paymentResponse))
                .onErrorResume(PaymentException.class, e -> {
                    log.warn("Payment not found or error retrieving status for ID {}: {}", paymentId, e.getMessage());
                    return Mono.just(ResponseEntity.notFound().build());
                })
                .onErrorResume(Exception.class, e -> {
                    log.error("Unexpected error while getting status for payment ID {}: {}", paymentId, e.getMessage(), e);
                    return Mono.just(ResponseEntity.internalServerError().build());
                });
    }
}
