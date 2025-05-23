
package com.triomphe.payment_service.application.dto;

import com.triomphe.payment_service.domain.model.ModePaiement;
import com.triomphe.payment_service.domain.model.StatutPaiement;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class PaiementResponse {
    private Long paiementId;
    private StatutPaiement statut;
    private Double montant;
    private ModePaiement modePaiement;
}
