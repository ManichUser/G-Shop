package com.triomphe.payment_service.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionPaiement {

    private Long id;
    private String type;
    private String clientId;
    private String commandeId;
    private Double montant;
    private LocalDateTime date;
}
