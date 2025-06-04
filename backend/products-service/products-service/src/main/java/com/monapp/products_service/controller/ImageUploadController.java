package com.monapp.products_service.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.monapp.products_service.model.Produit;
import com.monapp.products_service.repository.ProduitRepository;

@RestController
@RequestMapping("/api/images")
@CrossOrigin(origins = "*")
public class ImageUploadController {

    @Value("${upload.path}")
    private String uploadPath;

    private final ProduitRepository produitRepository;

    public ImageUploadController(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @PostMapping("/upload/{produitId}/{grossisteId}")
    public ResponseEntity<String> uploadImage(@PathVariable Long produitId, @PathVariable Long grossisteId, @RequestParam("image") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Fichier vide !");
        }

        Optional<Produit> optionalProduit = produitRepository.findById(produitId);
        if (optionalProduit.isEmpty()) {
            return ResponseEntity.status(404).body("Produit introuvable");
        }

        Produit produit = optionalProduit.get();
        if (!grossisteId.equals(produit.getIdGrossiste())) {
            return ResponseEntity.status(403).body("Ce produit n’appartient pas à ce grossiste.");
        }

        try {
            String nomFichier = System.currentTimeMillis() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
            Path cheminFichier = Paths.get(uploadPath + File.separator + nomFichier);
            Files.createDirectories(cheminFichier.getParent());
            Files.copy(file.getInputStream(), cheminFichier, StandardCopyOption.REPLACE_EXISTING);

            String cheminImage = "/images/" + nomFichier;
            produit.setImage(cheminImage);
            produitRepository.save(produit);

            return ResponseEntity.ok(cheminImage);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Erreur lors de l’upload.");
        }
    }
}
