package com.example.panier_service.service;

import com.example.panier_service.dto.CommandeRequestDTO;
import com.example.panier_service.dto.CommandeResponseDTO;
import com.example.panier_service.model.Commande;
import com.example.panier_service.repository.CommandeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CommandeServiceTest {

    @Mock
    private CommandeRepository commandeRepository;

    @InjectMocks
    private CommandeService commandeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Teste la création d'une commande via le service et vérifie le mapping et la sauvegarde.
     */
    @Test
    void testCreateCommande() {
        // Préparez vos mocks et DTO ici
        CommandeRequestDTO request = new CommandeRequestDTO();
        request.setUserId("user1");
        request.setProductId(123L);
        request.setQuantity(2);

        Commande savedCommande = Commande.builder()
                .id("cmd1")
                .userId("user1")
                .productId(123L)
                .quantity(2)
                .build();

        when(commandeRepository.save(any(Commande.class))).thenReturn(savedCommande);

        CommandeResponseDTO response = commandeService.createCommande(request);

        assertEquals("user1", response.getUserId());
        assertEquals(123L, response.getProductId());
        assertEquals(2, response.getQuantity());
    }

    /**
     * Exemple de squelette pour un autre test du service.
     */
    @Test
    void testSomething() {
        // Ajoutez ici vos assertions de test
    }
}
