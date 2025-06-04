package com.monapp.products_service.dtoTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.monapp.products_service.dto.ProduitRequestDTO;
import com.monapp.products_service.model.Produit;

class ProduitRequestDTOTest {

    @Test
    void toEntity_shouldMapAllFieldsCorrectly() {
        ProduitRequestDTO dto = new ProduitRequestDTO();
        dto.setNom("Farine");
        dto.setDescription("Farine de manioc");
        dto.setPrixUnitaire(new BigDecimal("2500"));
        dto.setSeuilMinimum(10);
        dto.setQuantiteDisponible(20);
        dto.setDateLimiteGroupage(LocalDateTime.of(2025, 6, 30, 0, 0));
        dto.setImage("farine.jpg");
        dto.setIdGrossiste(1L);

        Produit produit = dto.toEntity();

        assertEquals("Farine", produit.getNom());
        assertEquals("Farine de manioc", produit.getDescription());
        assertEquals(new BigDecimal("2500"), produit.getPrixUnitaire());
        assertEquals(10, produit.getSeuilMinimum());
        assertEquals(20, produit.getQuantiteDisponible());
        assertEquals(LocalDateTime.of(2025, 6, 30, 0, 0), produit.getDateLimiteGroupage());
        assertEquals("farine.jpg", produit.getImage());
        assertEquals(1L, produit.getIdGrossiste());
    }
}
