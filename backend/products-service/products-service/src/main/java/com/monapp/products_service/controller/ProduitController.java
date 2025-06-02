package com.monapp.products_service.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monapp.products_service.dto.ProduitRequestDTO;
import com.monapp.products_service.dto.ProduitResponseDTO;
import com.monapp.products_service.model.Produit;
import com.monapp.products_service.service.ProduitService;

@RestController
@RequestMapping("/api/produits")
@CrossOrigin(origins = "*")
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    // GET /api/produits
    @GetMapping
    public List<Produit> listerProduits() {
        return produitService.listerTousLesProduits();
    }

    // GET /api/produits/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Produit> obtenirProduit(@PathVariable Long id) {
        return produitService.obtenirProduitParId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/produits
    @PostMapping
    public ResponseEntity<Produit> creerProduit(@RequestBody Produit produit) {
        return ResponseEntity.ok(produitService.creerProduit(produit));
    }

    // PUT /api/produits/{id}
    @PutMapping("/{id}")
public ResponseEntity<ProduitResponseDTO> mettreAJourProduit(@PathVariable Long id, @RequestBody ProduitRequestDTO dto) {
    return produitService.mettreAJourProduit(id, dto)
            .map(ProduitResponseDTO::fromEntity)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}


    @GetMapping("/grossiste/{idGrossiste}")
    public List<ProduitResponseDTO> listerParGrossiste(@PathVariable Long idGrossiste) {
    return produitService.obtenirProduitsParGrossiste(idGrossiste).stream()
            .map(ProduitResponseDTO::fromEntity)
            .toList();
}


    // DELETE /api/produits/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerProduit(@PathVariable Long id) {
        boolean supprime = produitService.supprimerProduit(id);
        return supprime ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

