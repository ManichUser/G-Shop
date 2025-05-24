package com.example.panier_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "panier")
public class Commande {

    @Id
    private String id;

    private String userId;
    private String productId;
    private int quantity;

    @Builder.Default
    private Instant createdAt = Instant.now();
     @Builder.Default
    private CommandeStatus status = CommandeStatus.CREATED;
}
