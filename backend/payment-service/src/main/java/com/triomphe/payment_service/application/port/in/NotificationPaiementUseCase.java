package com.triomphe.payment_service.application.port.in;

public interface NotificationPaiementUseCase {
    void notifierFournisseur(String commandeId);
}
