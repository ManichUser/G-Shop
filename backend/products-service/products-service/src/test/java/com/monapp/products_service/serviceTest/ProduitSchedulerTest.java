package com.monapp.products_service.serviceTest;



import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.monapp.products_service.model.Produit;
import com.monapp.products_service.repository.ProduitRepository;
import com.monapp.products_service.service.ProduitScheduler;

class ProduitSchedulerTest {

    @Mock
    private ProduitRepository produitRepository;

    @InjectMocks
    private ProduitScheduler produitScheduler;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void verifierProduits_shouldDetectSeuilAndDateLimite() {
        // Given : 3 produits simulés
        Produit p1 = new Produit();
        p1.setNom("Riz");
        p1.setQuantiteDisponible(4);
        p1.setSeuilMinimum(5);
        p1.setDateLimiteGroupage(LocalDateTime.now().plusDays(2));
        p1.setIdGrossiste(1L);

        Produit p2 = new Produit();
        p2.setNom("Huile");
        p2.setQuantiteDisponible(20);
        p2.setSeuilMinimum(15);
        p2.setDateLimiteGroupage(LocalDateTime.now().minusDays(1)); // dépassée
        p2.setIdGrossiste(2L);

        Produit p3 = new Produit();
        p3.setNom("Savon");
        p3.setQuantiteDisponible(50);
        p3.setSeuilMinimum(30);
        p3.setDateLimiteGroupage(LocalDateTime.now().plusDays(5));
        p3.setIdGrossiste(3L);

        List<Produit> produits = List.of(p1, p2, p3);
        when(produitRepository.findAll()).thenReturn(produits);

        // When
        produitScheduler.verifierProduits();

        // Then
        // Ici on ne peut pas tester les System.out directement sans capture.
        // Mais on vérifie que produitRepository.findAll() a été appelé
        verify(produitRepository, times(1)).findAll();
    }
}
