package com.monapp.products_service.serviceTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.monapp.products_service.dto.ProduitRequestDTO;
import com.monapp.products_service.model.Produit;
import com.monapp.products_service.repository.ProduitRepository;
import com.monapp.products_service.service.ProduitService;

class ProduitServiceTest {

    @Mock
    private ProduitRepository produitRepository;

    @InjectMocks
    private ProduitService produitService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreerProduit() {
        Produit produit = new Produit();
        produit.setNom("Huile");

        when(produitRepository.save(produit)).thenReturn(produit);

        Produit result = produitService.creerProduit(produit);

        assertEquals("Huile", result.getNom());
        verify(produitRepository, times(1)).save(produit);
    }

    @Test
    void testObtenirProduitParId() {
        Produit produit = new Produit();
        produit.setId(1L);

        when(produitRepository.findById(1L)).thenReturn(Optional.of(produit));

        Optional<Produit> result = produitService.obtenirProduitParId(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testListerTousLesProduits() {
        when(produitRepository.findAll()).thenReturn(List.of(new Produit(), new Produit()));

        List<Produit> result = produitService.listerTousLesProduits();

        assertEquals(2, result.size());
        verify(produitRepository).findAll();
    }

    @Test
    void testObtenirProduitsParGrossiste() {
        Produit p1 = new Produit();
        Produit p2 = new Produit();
        when(produitRepository.findByIdGrossiste(2L)).thenReturn(List.of(p1, p2));

        List<Produit> result = produitService.obtenirProduitsParGrossiste(2L);
        assertEquals(2, result.size());
    }

    @Test
    void testMettreAJourProduit() {
        Produit existing = new Produit();
        existing.setId(1L);

        ProduitRequestDTO dto = new ProduitRequestDTO();
        dto.setNom("Maïs");
        dto.setPrixUnitaire(new BigDecimal("500"));
        dto.setSeuilMinimum(10);
        dto.setQuantiteDisponible(5);
        dto.setDateLimiteGroupage(LocalDateTime.now().plusDays(2));
        dto.setImage("mais.jpg");
        dto.setIdGrossiste(3L);

        when(produitRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(produitRepository.save(any(Produit.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Optional<Produit> result = produitService.mettreAJourProduit(1L, dto);

        assertTrue(result.isPresent());
        assertEquals("Maïs", result.get().getNom());
        assertEquals(3L, result.get().getIdGrossiste());
    }

    @Test
    void testSupprimerProduit_success() {
        when(produitRepository.existsById(1L)).thenReturn(true);

        boolean deleted = produitService.supprimerProduit(1L);

        assertTrue(deleted);
        verify(produitRepository).deleteById(1L);
    }

    @Test
    void testSupprimerProduit_notFound() {
        when(produitRepository.existsById(2L)).thenReturn(false);

        boolean deleted = produitService.supprimerProduit(2L);

        assertFalse(deleted);
    }
}

