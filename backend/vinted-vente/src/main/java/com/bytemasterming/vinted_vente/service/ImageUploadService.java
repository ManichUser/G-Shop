package com.bytemasterming.vinted_vente.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import com.bytemasterming.vinted_vente.repository.ImageRepository;

@Service
public class ImageUploadService {

    @Value("${upload.directory}")
    private String uploadDir;

    ImageRepository imageRepository;

    @Value("${server.url}")
    private String serverUrl;

    @Autowired
    private ImageRepository imageRepository;

    public Map<String, String> uploadImage(MultipartFile file, String productId) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Fichier vide");
        }

        // Obtenir l'extension du fichier (ex : .jpg)
        String extension = Objects.requireNonNull(file.getOriginalFilename())
                                  .substring(file.getOriginalFilename().lastIndexOf("."));

        // Générer un nom de fichier unique
        String fileName = UUID.randomUUID() + extension;

        // Définir le chemin de stockage complet
        Path filePath = Paths.get(uploadDir, fileName);

        // Créer les dossiers s'ils n'existent pas
        Files.createDirectories(filePath.getParent());

        // Sauvegarder le fichier sur le disque
        file.transferTo(filePath.toFile());
        Image image = new Image(fileName, imageUrl, product); // tu dois récupérer ou passer le produit
        imageRepository.save(image);

        // Construire l'URL d'accès à l'image
        String imageUrl = serverUrl + "/images/" + fileName;

        // Retourner un map avec l'URL
        return Map.of("imageUrl", imageUrl);
    }
}
