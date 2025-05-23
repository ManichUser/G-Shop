package com.triomphe.payment_service.application.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RemboursementClientDTO {
    private String clientId;
    private double montant;
    private String commandeId;
}
