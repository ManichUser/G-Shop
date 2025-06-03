package com.bytemasterming.vinted_vente.controller;

import com.bytemasterming.vinted_vente.model.ProductVinted;
import com.bytemasterming.vinted_vente.repository.ImageRepository;
import com.bytemasterming.vinted_vente.repository.ProductVintedRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.mock.web.MockMultipartFile;


@WebMvcTest(ImageUploadController.class)
class ImageUploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductVintedRepository productRepository;

    @MockBean
    private ImageRepository imageRepository;

    private String productId;
    private ProductVinted mockProduct;

    @BeforeEach
    void setUp() {
        productId = UUID.randomUUID().toString();
        mockProduct = new ProductVinted();
        mockProduct.setId(productId);

        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));
    }

    @Test
    void testUploadImage_shouldReturnImageUrl() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file", "test.jpg", "image/jpeg", "dummy-image-data".getBytes());

        mockMvc.perform(multipart("/api/images/upload")
                        .file(file)
                        .param("productId", productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.imageUrl").exists());
    }

    @Test
    void testUploadImage_shouldReturnErrorIfFileEmpty() throws Exception {
        MockMultipartFile emptyFile = new MockMultipartFile("file", new byte[0]);

        mockMvc.perform(multipart("/api/images/upload")
                        .file(emptyFile)
                        .param("productId", productId))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Fichier vide"));
    }
}
