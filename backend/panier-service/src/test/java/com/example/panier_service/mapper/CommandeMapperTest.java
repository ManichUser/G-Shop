package com.example.panier_service.mapper;

import com.example.panier_service.dto.CommandeRequestDTO;
import com.example.panier_service.dto.CommandeResponseDTO;
import com.example.panier_service.model.Commande;
import com.example.panier_service.model.CommandeStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class CommandeMapperTest {

    /**
     * Vérifie que le mapping d'un CommandeRequestDTO vers Commande fonctionne correctement
     * et que tous les champs sont bien transférés.
     */
    @Test
    @DisplayName("CommandeMapper.toEntity doit mapper correctement un CommandeRequestDTO")
    void testToEntity() {
        CommandeRequestDTO dto = new CommandeRequestDTO();
        dto.setUserId("user1");
        dto.setProductId(123L);
        dto.setQuantity(2);

        Commande commande = CommandeMapper.toEntity(dto);

        assertAll("Vérification du mapping CommandeRequestDTO -> Commande",
            () -> assertEquals("user1", commande.getUserId(), "userId incorrect"),
            () -> assertEquals(123L, commande.getProductId(), "productId incorrect"),
            () -> assertEquals(2, commande.getQuantity(), "quantity incorrect"),
            () -> assertNotNull(commande.getCreatedAt(), "createdAt ne doit pas être null"),
            () -> assertEquals(CommandeStatus.CREATED, commande.getStatus(), "status doit être CREATED")
        );
    }

    /**
     * Vérifie que le mapping gère correctement les valeurs nulles ou par défaut dans CommandeRequestDTO.
     */
    @Test
    @DisplayName("CommandeMapper.toEntity doit gérer les valeurs nulles")
    void testToEntityWithNullValues() {
        CommandeRequestDTO dto = new CommandeRequestDTO();
        // Tous les champs sont null ou 0

        Commande commande = CommandeMapper.toEntity(dto);

        assertNull(commande.getUserId(), "userId doit être null");
        assertNull(commande.getProductId(), "productId doit être null");
        assertEquals(0, commande.getQuantity(), "quantity doit être 0");
        assertNotNull(commande.getCreatedAt(), "createdAt ne doit pas être null");
        assertEquals(CommandeStatus.CREATED, commande.getStatus(), "status doit être CREATED");
    }
}
