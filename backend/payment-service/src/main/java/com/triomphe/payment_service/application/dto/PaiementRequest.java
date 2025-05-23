package com.triomphe.payment_service.application.dto;

import com.triomphe.payment_service.domain.model.ModePaiement;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PaiementRequest {
    private String commandeId;
    private String clientId;
    private Double montant;
    private ModePaiement modePaiement; // Il doit matcher les valeurs exactes de l'enum


}
