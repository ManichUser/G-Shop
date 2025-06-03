package com.bytemasterming.vinted_vente.service;

import com.bytemasterming.vinted_vente.model.Image;
import com.bytemasterming.vinted_vente.model.ProductVinted;
import com.bytemasterming.vinted_vente.repository.ImageRepository;
import com.bytemasterming.vinted_vente.repository.ProductVintedRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ImageUploadServiceTest {

    @InjectMocks
    private ImageUploadService imageUploadService;

    @Mock
    private ProductVintedRepository productRepository;

    @Mock
    private ImageRepository imageRepository;

    // Variables de config à injecter (simulées)
    private String uploadDir = "uploads/images";
    private String serverUrl = "http://localhost:8080";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Injection des valeurs manuellement si tu utilises @Value dans service
        imageUploadService.setUploadDir(uploadDir);
        imageUploadService.setServerUrl(serverUrl);
    }

    @Test
    void uploadImage_shouldSaveImageAndReturnUrl() throws IOException {
        // 1. Préparer un fichier factice (mock)
        MultipartFile file = new MockMultipartFile(
                "file",
                "test.jpg",
                "image/jpeg",
                "fake-image-content".getBytes()
        );

        // 2. Préparer un produit mocké retourné par le repo
        String productId = UUID.randomUUID().toString();
        ProductVinted product = new ProductVinted();
        // suppose setter pour id
        product.setId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // 3. Mock de la sauvegarde Image (on veut juste vérifier qu'on l'appelle)
        when(imageRepository.save(any(Image.class))).thenAnswer(i -> i.getArguments()[0]);

        // 4. Appeler la méthode à tester
        var result = imageUploadService.uploadImage(file, productId);

        // 5. Vérifier le résultat (contient "imageUrl")
        assertNotNull(result.get("imageUrl"));
        assertTrue(result.get("imageUrl").contains(serverUrl));

        // 6. Vérifier que save a été appelé 1 fois
        verify(imageRepository, times(1)).save(any(Image.class));

        // Optionnel : vérifier que le fichier existe sur disque
        Path expectedFile = Path.of(uploadDir).resolve(
                result.get("imageUrl").substring(result.get("imageUrl").lastIndexOf("/") + 1)
        );
        assertTrue(Files.exists(expectedFile));

        // Nettoyage fichier créé (important en test)
        Files.deleteIfExists(expectedFile);
    }

    @Test
    void uploadImage_shouldThrowException_whenFileEmpty() {
        MultipartFile emptyFile = new MockMultipartFile("file", new byte[0]);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            imageUploadService.uploadImage(emptyFile, "someId");
        });

        assertEquals("Fichier vide", exception.getMessage());
    }

    @Test
    void uploadImage_shouldThrowException_whenProductNotFound() {
        MultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", "data".getBytes());

        when(productRepository.findById("unknownId")).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            imageUploadService.uploadImage(file, "unknownId");
        });

        assertEquals("Produit non trouvé", exception.getMessage());
    }
}
