package com.monapp.products_service.controllerTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monapp.products_service.model.Produit;
import com.monapp.products_service.repository.ProduitRepository;

@SpringBootTest
@AutoConfigureMockMvc
class ProduitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreerEtObtenirProduit() throws Exception {
        Produit produit = new Produit();
        produit.setNom("Farine");
        produit.setDescription("Farine de manioc");
        produit.setPrixUnitaire(new BigDecimal("2500"));
        produit.setSeuilMinimum(10);
        produit.setQuantiteDisponible(20);
        produit.setDateLimiteGroupage(LocalDateTime.now().plusDays(3));
        produit.setIdGrossiste(1L);
        produit.setImage("farine.jpg");

        // POST
        mockMvc.perform(post("/api/produits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produit)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Farine"));

        String response = mockMvc.perform(post("/api/produits")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(produit)))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.nom").value("Farine"))
           .andReturn()
           .getResponse()
           .getContentAsString();

        Produit produitCree = objectMapper.readValue(response, Produit.class);

        mockMvc.perform(get("/api/produits/" + produitCree.getId()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nom").value("Farine"));

    }

    @Test
    void testSupprimerProduit() throws Exception {
        Produit produit = new Produit();
        produit.setNom("Savon");
        produit.setIdGrossiste(2L);
        produitRepository.save(produit);

        mockMvc.perform(delete("/api/produits/" + produit.getId()))
                .andExpect(status().isNoContent());
    }
}

