
package com.triomphe.payment_service.application.port.in;

import com.triomphe.payment_service.application.dto.PaiementRequest;
import com.triomphe.payment_service.application.dto.PaiementResponse;

public interface PaiementUseCase {
    PaiementResponse demarrerPaiement(PaiementRequest request);
}
