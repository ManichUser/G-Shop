package com.bytemasterming.vinted_vente.integration;

import com.bytemasterming.vinted_vente.model.ProductVinted;
import com.bytemasterming.vinted_vente.repository.ProductVintedRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ImageUploadIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductVintedRepository productRepository;

    private String productId;

    @BeforeEach
    void setUp() {
        ProductVinted product = new ProductVinted();
        product.setId(UUID.randomUUID().toString());
        product.setNom("Chaussures test");
        product.setPrix(50.0);
        product.setCategorie("Chaussures");

        productId = productRepository.save(product).getId();
    }

    @Test
    void testImageUploadIntegration() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file", "test.jpg", "image/jpeg", "integration test image".getBytes());

        mockMvc.perform(multipart("/api/images/upload")
                        .file(file)
                        .param("productId", productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.imageUrl").exists());
    }
}