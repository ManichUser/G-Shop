package com.example.panier_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommandeRequestDTO {

    @NotBlank
    private String userId;

    @NotBlank
    private String productId;

    @Min(1)
    private int quantity;
   
}
