package com.triomphe.payment_service.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private CommandeId commandeId;

    @Embedded
    private ClientId clientId;

    @Enumerated(EnumType.STRING)
    private StatutPaiement statut;

    @Enumerated(EnumType.STRING)
    private ModePaiement modePaiement;

    private Double montant;

    private LocalDateTime dateCreation;

    private LocalDateTime dateExpiration;
}
