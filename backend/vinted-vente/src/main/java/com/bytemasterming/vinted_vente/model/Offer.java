package com.bytemasterming.vinted_vente.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "offers")
public class Offer {

    @Id
    private String id;  // MongoDB gère les ID sous forme de String

    private String productId;
    private String buyerId;
    private Double offerPrice;       
    private double montant;         
    private LocalDateTime offerDate = LocalDateTime.now();  
    private OfferStatus status = OfferStatus.EN_ATTENTE;  
    private String message;           
}
