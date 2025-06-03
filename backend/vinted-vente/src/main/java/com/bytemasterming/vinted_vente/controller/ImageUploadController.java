package com.bytemasterming.vinted_vente.controller;

import com.bytemasterming.vinted_vente.model.Image;
import com.bytemasterming.vinted_vente.model.ProductVinted;
import com.bytemasterming.vinted_vente.repository.ImageRepository;
import com.bytemasterming.vinted_vente.repository.ProductVintedRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@RestController
@RequestMapping("/api/images")
public class ImageUploadController {

    @Value("${upload.directory}")
    private String uploadDir;

    @Value("${server.url}")
    private String serverUrl;

    private final ProductVintedRepository productRepository;
    private final ImageRepository imageRepository;

    public ImageUploadController(ProductVintedRepository productRepository, ImageRepository imageRepository) {
        this.productRepository = productRepository;
        this.imageRepository = imageRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("file") MultipartFile file,
                                                           @RequestParam("productId") String productId) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Fichier vide"));
        }

        try {
            // 1. Générer un nom de fichier unique
            String extension = Objects.requireNonNull(file.getOriginalFilename())
                                      .substring(file.getOriginalFilename().lastIndexOf("."));
            String fileName = UUID.randomUUID() + extension;

            // 2. Créer le chemin et enregistrer l'image physiquement
            Path filePath = Paths.get(uploadDir, fileName);
            Files.createDirectories(filePath.getParent());
            file.transferTo(filePath.toFile());

            // 3. Construire l'URL publique
            String imageUrl = serverUrl + "/images/" + fileName;

            // 4. Récupérer le produit
            Optional<ProductVinted> optionalProduct = productRepository.findById(productId);
            if (optionalProduct.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Produit non trouvé"));
            }
            ProductVinted product = optionalProduct.get();

            // 5. Créer l'entité image et la lier au produit
            Image image = new Image(fileName, imageUrl, product);
            imageRepository.save(image);

            // 6. Retourner l'URL
            return ResponseEntity.ok(Map.of("imageUrl", imageUrl));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("error", "Erreur d’upload"));
        }
    }
}
