package com.example.panier_service.mapper;


import com.example.panier_service.dto.CommandeRequestDTO;
import com.example.panier_service.dto.CommandeResponseDTO;
import com.example.panier_service.model.Commande;

public class CommandeMapper {

    public static Commande toEntity(CommandeRequestDTO dto) {
        return Commande.builder()
                .userId(dto.getUserId())
                .productId(dto.getProductId())
                .quantity(dto.getQuantity())
                .build();
    }

    public static CommandeResponseDTO toDto(Commande commande) {
        return CommandeResponseDTO.builder()
                .id(commande.getId())
                .userId(commande.getUserId())
                .productId(commande.getProductId())
                .quantity(commande.getQuantity())
                .createdAt(commande.getCreatedAt())
                 .status(commande.getStatus())
                .build();
    }
}

