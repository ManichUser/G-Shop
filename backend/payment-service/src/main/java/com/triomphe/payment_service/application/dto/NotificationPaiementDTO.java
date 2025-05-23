package com.triomphe.payment_service.application.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotificationPaiementDTO {
    private String commandeId;
    private String message;
}
