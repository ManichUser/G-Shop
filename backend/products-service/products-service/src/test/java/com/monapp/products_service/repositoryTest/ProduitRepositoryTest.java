package com.monapp.products_service.repositoryTest;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.monapp.products_service.model.Produit;
import com.monapp.products_service.repository.ProduitRepository;

@DataJpaTest
class ProduitRepositoryTest {

    @Autowired
    private ProduitRepository produitRepository;

    @Test
    void findByIdGrossiste_shouldReturnMatchingProduits() {
        // Given : deux produits avec idGrossiste = 1
        Produit p1 = new Produit();
        p1.setNom("Riz");
        p1.setDescription("Riz parfumé");
        p1.setPrixUnitaire(new BigDecimal("1500"));
        p1.setSeuilMinimum(10);
        p1.setQuantiteDisponible(8);
        p1.setDateLimiteGroupage(LocalDateTime.now().plusDays(2));
        p1.setImage("riz.jpg");
        p1.setIdGrossiste(1L);

        Produit p2 = new Produit();
        p2.setNom("Huile");
        p2.setDescription("Huile 5L");
        p2.setPrixUnitaire(new BigDecimal("4500"));
        p2.setSeuilMinimum(5);
        p2.setQuantiteDisponible(10);
        p2.setDateLimiteGroupage(LocalDateTime.now().plusDays(1));
        p2.setImage("huile.jpg");
        p2.setIdGrossiste(1L);

        Produit p3 = new Produit();
        p3.setNom("Savon");
        p3.setIdGrossiste(2L);

        produitRepository.saveAll(List.of(p1, p2, p3));

        // When
        List<Produit> result = produitRepository.findByIdGrossiste(1L);

        // Then
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(p -> p.getIdGrossiste() == 1L));
    }
}
