package com.bytemasterming.model;

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
    private String id;

    private String productId;
    private String buyerId;
    private Double offerPrice;        // Prix proposé par l'acheteur
    private double montant;           // Montant final (si négocié)
    private LocalDateTime offerDate;  // Date de l'offre
    private OfferStatus status;       // EN_ATTENTE, ACCEPTEE, etc.
    private String message;           // Message personnalisé optionnel
}
