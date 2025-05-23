package com.triomphe.payment_service.application.port.in;

import com.triomphe.payment_service.application.dto.ValidationPaiementDTO;

public interface ValidationPaiementFournisseurUseCase {
    void validerPaiement(ValidationPaiementDTO dto);
}
