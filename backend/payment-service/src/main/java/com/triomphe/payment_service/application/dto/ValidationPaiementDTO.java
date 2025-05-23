package com.triomphe.payment_service.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ValidationPaiementDTO {
    private Long paiementId;
    private boolean accepte; // true = accepté, false = refusé
}
