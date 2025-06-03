package com.bytemasterming.vinted_vente.dto;

import com.bytemasterming.vinted_vente.model.OfferStatus;
import java.time.LocalDateTime;

public class OfferDTO {
    private Long id;
    private String productId;
    private String buyerId;
    private Double offerPrice;
    private double montant;
    private LocalDateTime offerDate;
    private OfferStatus status;
    private String message;
}