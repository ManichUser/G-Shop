package com.gshop.payment_service.repository;

import com.gshop.payment_service.model.Payment; // Import de l'entité Payment
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono; // Pour les opérations réactives

import java.util.Optional;
import java.util.UUID;

/**
 * Interface de repository pour l'entité Payment.
 */
@Repository // Indique que c'est un composant de repository Spring
public interface PaymentRepository extends JpaRepository<Payment, UUID> {


    Optional<Payment> findByExternalOrderId(String externalOrderId);


    Optional<Payment> findByExternalTransactionId(String externalTransactionId);


}
