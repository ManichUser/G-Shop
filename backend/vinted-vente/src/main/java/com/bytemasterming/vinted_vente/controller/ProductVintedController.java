package com.bytemasterming.vinted_vente.controller;

import com.bytemasterming.vinted_vente.model.Offer;
import com.bytemasterming.vinted_vente.model.ProductVinted;
import com.bytemasterming.vinted_vente.model.ProductStatus;
import com.bytemasterming.vinted_vente.service.ProduitVintedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vinted")
public class ProductVintedController {

    private final ProduitVintedService produitService;

    @Autowired
    public ProductVintedController(ProduitVintedService produitService) {
        this.produitService = produitService;
    }

    @PostMapping
    public ProductVinted ajouterProduit(@RequestBody ProductVinted produitVinted) {
        return produitService.ajouterProduit(produitVinted);
    }

    @GetMapping("/disponible")
    public List<ProductVinted> produitsDisponibles() {
        return produitService.getProduitsDisponibles();
    }

    @GetMapping("/utilisateur/{idUser}")
    public List<ProductVinted> produitsParUtilisateur(@PathVariable String idUser) {
        return produitService.getProduitsParUtilisateur(idUser);
    }

    @GetMapping("/utilisateur/{idUser}/status/{status}")
    public List<ProductVinted> produitsUtilisateurParStatut(@PathVariable String idUser, @PathVariable ProductStatus status) {
        return produitService.getProduitsParUtilisateurEtStatut(idUser, status);
    }

    @GetMapping("/recherche")
    public List<ProductVinted> rechercherParNom(@RequestParam String nomProduit) {
        return produitService.rechercherProduitsParNom(nomProduit);
    }

    @GetMapping("/categorie/{categorie}")
    public List<ProductVinted> produitsParCategorie(@PathVariable String categorie) {
        return produitService.getProduitsParCategorie(categorie);
    }

    @GetMapping
    public List<ProductVinted> tousLesProduits() {
        return produitService.getTousLesProduits();
    }

    @PostMapping("/{productId}/offres")
    public Offer creerOffre(@PathVariable String productId, @RequestBody Offer offre) {
        return produitService.faireOffre(productId, offre);
    }

    @PutMapping("/{productId}/offres/{offerId}/valider")
    public ProductVinted validerOffre(@PathVariable String productId, @PathVariable Long offerId) {
        return produitService.validerOffre(productId, offerId);
    }

}