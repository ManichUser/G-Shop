package com.monapp.products_service.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monapp.products_service.model.Produit;
import com.monapp.products_service.repository.ProduitRepository;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    // Créer un nouveau produit
    public Produit creerProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    // Lire un produit par ID
    public Optional<Produit> obtenirProduitParId(Long id) {
        return produitRepository.findById(id);
    }

    // Lire tous les produits
    public List<Produit> listerTousLesProduits() {
        return produitRepository.findAll();
    }

    // Mettre à jour un produit existant
    public Optional<Produit> mettreAJourProduit(Long id, Produit produitModifie) {
        return produitRepository.findById(id).map(produit -> {
            produit.setNom(produitModifie.getNom());
            produit.setDescription(produitModifie.getDescription());
            produit.setPrixUnitaire(produitModifie.getPrixUnitaire());
            produit.setSeuilMinimum(produitModifie.getSeuilMinimum());
            produit.setQuantiteDisponible(produitModifie.getQuantiteDisponible());
            produit.setDateLimiteGroupage(produitModifie.getDateLimiteGroupage());
            produit.setImage(produitModifie.getImage());
            return produitRepository.save(produit);
        });
    }

    // Supprimer un produit
    public boolean supprimerProduit(Long id) {
        if (produitRepository.existsById(id)) {
            produitRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

