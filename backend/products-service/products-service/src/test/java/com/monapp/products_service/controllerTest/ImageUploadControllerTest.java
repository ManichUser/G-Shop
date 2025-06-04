package com.monapp.products_service.controllerTest;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.monapp.products_service.model.Produit;
import com.monapp.products_service.repository.ProduitRepository;

@SpringBootTest
@AutoConfigureMockMvc
class ImageUploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProduitRepository produitRepository;

    @Test
    void testUploadImageShouldSucceed() throws Exception {
        Produit produit = new Produit();
        produit.setNom("Riz");
        produit.setIdGrossiste(5L);
        produitRepository.save(produit);

        MockMultipartFile image = new MockMultipartFile(
                "image",
                "test.jpg",
                "image/jpeg",
                "contenu fictif".getBytes()
        );

        mockMvc.perform(multipart("/api/images/upload/" + produit.getId() + "/5")
                        .file(image))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("/images/")));
    }

    @Test
    void testUploadImageWithWrongGrossisteIdShouldFail() throws Exception {
        Produit produit = new Produit();
        produit.setNom("Huile");
        produit.setIdGrossiste(1L);
        produitRepository.save(produit);

        MockMultipartFile image = new MockMultipartFile(
                "image",
                "huile.jpg",
                "image/jpeg",
                "data".getBytes()
        );

        mockMvc.perform(multipart("/api/images/upload/" + produit.getId() + "/999")
                        .file(image))
                .andExpect(status().isForbidden());
    }
}
