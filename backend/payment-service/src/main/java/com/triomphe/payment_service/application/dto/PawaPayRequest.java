package com.triomphe.payment_service.application.dto;

import com.triomphe.payment_service.domain.model.ModePaiement;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class PawaPayRequest {
    private String customer;
    private double amount;
    private ModePaiement modePaiement;  // "ORANGE_MONEY", "MTN_MONEY", "CARTE".
    private double payeeNote;

    public PawaPayRequest(String customer, double amount, String modePaiement) {
    }
}


