package com.example.panier_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
@Data
public class CommandeRequestDTO {

    @NotNull
    private String userId;

    @NotNull
    private Long productId;

    @Min(1)
    private int quantity;
   
}
