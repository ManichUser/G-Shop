package com.bytemasterming.vinted_vente.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.bytemasterming.vinted_vente.model.ProductVinted;
import com.bytemasterming.vinted_vente.model.ProductStatus;
import com.bytemasterming.vinted_vente.repository.ProductVintedRepository;

@RestController
@RequestMapping("/api/vinted")
public class ProductVintedController {

  
    private ProductVintedRepository productVintedRepository;

    ProductVintedController(ProductVintedRepository productVintedRepository) {
        this.productVintedRepository = productVintedRepository;
    }
    
    @PostMapping
    public ProductVinted ajouterProduit(@RequestBody ProductVinted produitVinted) {
        produitVinted.setStatus(ProductStatus.DISPONIBLE);
        return productVintedRepository.save(produitVinted);
    }


    @GetMapping("/disponible")
    public List<ProductVinted> produitsDisponibles() {
        return productVintedRepository.findByStatus(ProductStatus.DISPONIBLE);
    }


    @GetMapping("/utilisateur/{idUser}")
    public List<ProductVinted> produitsParUtilisateur(@PathVariable String idUser) {
        return productVintedRepository.findByIdUser(idUser);
    }

   
    @GetMapping("/utilisateur/{idUser}/status/{status}")  // Produits d’un utilisateur avec un statut
    public List<ProductVinted> produitsUtilisateurParStatut(@PathVariable String idUser, @PathVariable ProductStatus status) {
        return productVintedRepository.findByIdUserAndStatus(idUser, status);
    }

    
    @GetMapping("/recherche") // Recherche par nom (partiel)
    public List<ProductVinted> rechercherParNom(@RequestParam String nomProduit) {
        return productVintedRepository.findByProductNameContaining(nomProduit);
    }


    @GetMapping("/categorie/{categorie}")
    public List<ProductVinted> produitsParCategorie(@PathVariable String categorie) {
        return productVintedRepository.findByCategory(categorie);
    }
    @GetMapping
    public List<ProductVinted> tousLesProduits() {
        return productVintedRepository.findAll();
    }
}
