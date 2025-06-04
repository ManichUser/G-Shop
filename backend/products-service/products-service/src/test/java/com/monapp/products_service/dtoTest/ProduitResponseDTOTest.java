package com.monapp.products_service.dtoTest;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.monapp.products_service.dto.ProduitResponseDTO;
import com.monapp.products_service.model.Produit;

class ProduitResponseDTOTest {

    @Test
    void fromEntity_shouldMapAllFieldsAndComputeFlags() {
        Produit produit = new Produit();
        produit.setId(1L);
        produit.setNom("Tomates");
        produit.setDescription("Tomates fraîches");
        produit.setPrixUnitaire(new BigDecimal("4500"));
        produit.setSeuilMinimum(10);
        produit.setQuantiteDisponible(8);
        produit.setDateLimiteGroupage(LocalDateTime.now().minusDays(1));
        produit.setImage("tomates.png");
        produit.setIdGrossiste(3L);
        produit.setDateCreation(LocalDateTime.now().minusDays(2));

        ProduitResponseDTO dto = ProduitResponseDTO.fromEntity(produit);

        assertEquals("Tomates", dto.getNom());
        assertEquals("Tomates fraîches", dto.getDescription());
        assertEquals(new BigDecimal("4500"), dto.getPrixUnitaire());
        assertEquals(8, dto.getQuantiteDisponible());
        assertTrue(dto.isSeuilAtteint());
        assertTrue(dto.isDateLimiteDepassee());
    }
}
