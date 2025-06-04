package com.monapp.products_service.configTest;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WebConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${upload.path}")
    private String uploadPath;

    private final String fileName = "test-image.txt";

    @BeforeEach
    void setup() throws Exception {
        // Crée un fichier test dans uploads/
        Path filePath = Path.of(uploadPath, fileName);
        Files.createDirectories(filePath.getParent());
        Files.writeString(filePath, "contenu de test");
    }

    @Test
    void testImageIsAccessibleViaImagesPath() throws Exception {
        mockMvc.perform(get("/images/" + fileName))
                .andExpect(status().isOk())
                .andExpect(content().string("contenu de test"));
    }
}

