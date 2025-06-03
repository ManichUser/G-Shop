package com.example.panier_service.dto;

import com.example.panier_service.model.CommandeStatus;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class CommandeResponseDTO {

    private String id;
    private String userId;
    private Long productId;
    private int quantity;
    private Instant createdAt;
      private CommandeStatus status;
    
 private int prix;
}
