package com.bytemasterming.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.bytemasterming.model.ProductVinted;
import com.bytemasterming.model.ProductStatus;
import com.bytemasterming.repository.ProductVintedRepository;

@RestController
@RequestMapping("/api/vinted")
public class ProductVintedController {

    @Autowired
    private ProductVintedRepository productVintedRepository;

    // Créer un produit
    @PostMapping
    public ProductVinted ajouterProduit(@RequestBody ProductVinted produitVinted) {
        produitVinted.setStatus(ProductStatus.DISPONIBLE);
        return productVintedRepository.save(produitVinted);
    }

    // Produits disponibles
    @GetMapping("/disponible")
    public List<ProductVinted> produitsDisponibles() {
        return productVintedRepository.findByStatus(ProductStatus.DISPONIBLE);
    }

    // Produits d'un utilisateur
    @GetMapping("/utilisateur/{idUser}")
    public List<ProductVinted> produitsParUtilisateur(@PathVariable String idUser) {
        return productVintedRepository.findByIdUser(idUser);
    }

    // Produits d’un utilisateur avec un statut
    @GetMapping("/utilisateur/{idUser}/status/{status}")
    public List<ProductVinted> produitsUtilisateurParStatut(@PathVariable String idUser, @PathVariable ProductStatus status) {
        return productVintedRepository.findByIdUserAndStatus(idUser, status);
    }

    // Recherche par nom (partiel)
    @GetMapping("/recherche")
    public List<ProductVinted> rechercherParNom(@RequestParam String nomProduit) {
        return productVintedRepository.findByProductNameContaining(nomProduit);
    }

    // Produits d'une catégorie
    @GetMapping("/categorie/{categorie}")
    public List<ProductVinted> produitsParCategorie(@PathVariable String categorie) {
        return productVintedRepository.findByCategory(categorie);
    }
}
