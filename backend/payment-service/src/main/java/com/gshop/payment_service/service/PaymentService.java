package com.gshop.payment_service.service;

import com.gshop.payment_service.dto.CallbackRequest;
import com.gshop.payment_service.dto.PaymentRequest;
import com.gshop.payment_service.dto.PaymentResponse;
import com.gshop.payment_service.exceptions.PaymentException;
import com.gshop.payment_service.model.Payment;
import com.gshop.payment_service.model.enums.PaymentStatusEnum;
import com.gshop.payment_service.repository.PaymentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono; // Pour la programmation réactive
import reactor.core.scheduler.Schedulers; // Pour gérer les opérations bloquantes de la DB

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 Service principal de gestion des paiements.
 */
@Service
@RequiredArgsConstructor
@Slf4j // Active le logger Lombok pour cette classe
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrangeMoneyService orangeMoneyService;
    private final MTNMoMoService mtnMoMoService;
    // private final BankTransferService bankTransferService; // fonction a venir

    /**
      Initialise un nouveau paiement basé sur la requête reçue.
     */
    public Mono<PaymentResponse> initiatePayment(PaymentRequest request) {
        log.info("Initiating payment for external order ID: {}", request.getExternalOrderId());

        // 1. Vérifier l'idempotence: S'assurer que le paiement n'existe pas déjà pour cette externalOrderId
        // nous utilisons Mono.defer pour s'assurer que la logique est exécutée de manière réactive
        return Mono.defer(() -> {
                    Optional<Payment> existingPayment = paymentRepository.findByExternalOrderId(request.getExternalOrderId());
                    if (existingPayment.isPresent()) {
                        log.warn("Payment with externalOrderId {} already exists. Returning existing payment response.", request.getExternalOrderId());
                        return Mono.just(mapToPaymentResponse(existingPayment.get(), "Paiement déjà initié."));
                    }
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.defer(() -> {

                    // 2. Créer une nouvelle entité Payment avec le statut PENDING
                    Payment payment = new Payment();
                    payment.setExternalOrderId(request.getExternalOrderId());
                    payment.setAmount(request.getAmount());
                    payment.setCurrency(request.getCurrency());
                    payment.setPaymentMethod(request.getPaymentMethod());
                    payment.setPayerIdentifier(request.getPayerIdentifier());
                    payment.setDescription(request.getDescription());
                    payment.setCallbackUrl(request.getCallbackUrl());
                    payment.setStatus(PaymentStatusEnum.PENDING); // Statut initial

                    // 3. Appeler le service externe approprié
                    Mono<String> externalRefMono;
                    String instructionMessage;

                    switch (request.getPaymentMethod().toUpperCase()) {
                        case "ORANGE_MONEY":
                            externalRefMono = orangeMoneyService.initiateIncomingPayment(request);
                            instructionMessage = "Veuillez confirmer le paiement sur votre mobile Orange Money.";
                            break;
                        case "MTN_MOMO":
                            externalRefMono = mtnMoMoService.initiateIncomingPayment(request);
                            instructionMessage = "Veuillez confirmer le paiement sur votre mobile MTN Mobile Money.";
                            break;
                        // paiement bancaire a venir
                        default:
                            return Mono.error(new PaymentException("Méthode de paiement non supportée : " + request.getPaymentMethod()));
                    }

                    // 4. Mettre à jour le paiement avec la référence externe et le sauvegarder
                    return externalRefMono
                            .publishOn(Schedulers.boundedElastic()) // Exécute l'opération DB sur un thread pool bloquant
                            .flatMap(externalRef -> {
                                payment.setExternalTransactionId(externalRef);
                                Payment savedPayment = paymentRepository.save(payment); // Sauvegarde bloquante
                                log.info("Payment {} initiated with external ref: {}", savedPayment.getId(), externalRef);
                                return Mono.just(mapToPaymentResponse(savedPayment, instructionMessage));
                            })
                            .cast(PaymentResponse.class) // <-- Ajouté pour lever l'ambiguïté de type
                            .onErrorResume(e -> {
                                log.error("Failed to initiate payment for external order ID {}: {}", request.getExternalOrderId(), e.getMessage());
                                // Optionnel: Mettre à jour le statut du paiement à FAILED en DB ici si l'initiation échoue
                                return Mono.error(new PaymentException("Échec de l'initiation du paiement : " + e.getMessage(), e));
                            });
                }));
    }

    /**
      Gère les callbacks (webhooks) reçus des fournisseurs de paiement externes.
      Met à jour le statut du paiement en base de données.
     */
    @Transactional // Assure que toutes les opérations de DB sont atomiques
    public Mono<Void> handleCallback(CallbackRequest callbackRequest, String paymentMethod) {
        log.info("Handling callback for method {}. External transaction ID: {}", paymentMethod, callbackRequest.getExternalTransactionId());

        // 1. Trouver le paiement interne par la référence externe du callback
        // On utilise Mono.fromCallable pour envelopper l'appel bloquant du repository
        return Mono.fromCallable(() -> paymentRepository.findByExternalTransactionId(callbackRequest.getExternalTransactionId()))
                .publishOn(Schedulers.boundedElastic()) // Exécute l'opération DB sur un thread pool dédié
                .flatMap(optionalPayment -> {
                    if (optionalPayment.isEmpty()) {
                        log.warn("Payment not found for external ref: {}. Cannot process callback.", callbackRequest.getExternalTransactionId());
                        return Mono.error(new PaymentException("Paiement non trouvé pour la référence externe : " + callbackRequest.getExternalTransactionId()));
                    }
                    Payment payment = optionalPayment.get();

                    // 2. Mettre à jour le statut du paiement
                    PaymentStatusEnum newStatus;
                    try {
                        newStatus = PaymentStatusEnum.valueOf(callbackRequest.getStatus().name()); // Convertir l'enum du callback
                    } catch (IllegalArgumentException e) {
                        log.warn("Statut inconnu reçu dans le callback pour paiement {}: {}", payment.getId(), callbackRequest.getStatus());
                        newStatus = PaymentStatusEnum.PENDING; // Par défaut, rester en attente si statut inconnu
                    }

                    // Mettre à jour le statut uniquement si le nouveau statut est plus "final" ou différent
                    if (!payment.getStatus().equals(newStatus)) {
                        payment.setStatus(newStatus);
                        payment.setUpdatedAt(LocalDateTime.now());
                        paymentRepository.save(payment); // Sauvegarde bloquante
                        log.info("Payment {} updated to status {}", payment.getId(), newStatus);


                    } else {
                        log.info("Payment {} status already {}. No update needed from callback.", payment.getId(), newStatus);
                    }
                    return Mono.empty(); // Indique que le traitement est terminé
                });
    }

    /**
     * Récupère le statut d'un paiement par son ID interne.
     *
     * @param paymentId L'ID interne du paiement.
     * @return Mono<PaymentResponse> contenant les détails du paiement.
     */
    public Mono<PaymentResponse> getPaymentStatus(UUID paymentId) {
        log.info("Fetching status for payment ID: {}", paymentId);
        return Mono.fromCallable(() -> paymentRepository.findById(paymentId))
                .publishOn(Schedulers.boundedElastic()) // Exécute l'opération DB sur un thread pool dédié
                .flatMap(optionalPayment -> optionalPayment.map(payment -> Mono.just(mapToPaymentResponse(payment, null)))
                        .orElseGet(() -> Mono.error(new PaymentException("Paiement non trouvé avec l'ID : " + paymentId))));
    }

    /**
     * Mappe une entité Payment à un DTO PaymentResponse.
     *
     * @param payment L'entité Payment.
     * @param message Un message optionnel à inclure dans la réponse.
     * @return Le DTO PaymentResponse.
     */

    private PaymentResponse mapToPaymentResponse(Payment payment, String message) {
        return new PaymentResponse(
                payment.getId(),
                payment.getExternalOrderId(),
                payment.getAmount(),
                payment.getCurrency(),
                payment.getPaymentMethod(),
                payment.getStatus(),
                payment.getExternalTransactionId(), // <-- CHAMPS AJOUTÉ : externalTransactionId
                message != null ? message : "Statut du paiement : " + payment.getStatus().name(),
                null, // redirectUrl, à définir si l'API externe le fournit
                payment.getCreatedAt()
        );

    }}
