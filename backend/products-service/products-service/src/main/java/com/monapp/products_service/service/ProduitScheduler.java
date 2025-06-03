package com.monapp.products_service.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.monapp.products_service.model.Produit;
import com.monapp.products_service.repository.ProduitRepository;

import jakarta.transaction.Transactional;

@Service
public class ProduitScheduler {

    @Autowired
    private ProduitRepository produitRepository;

    // S'exécute toutes les heures à HH:00
    @Scheduled(cron = "0 0 * * * *")
    @Transactional
    public void verifierProduits() {
        LocalDateTime maintenant = LocalDateTime.now();
        List<Produit> produits = produitRepository.findAll();

        for (Produit produit : produits) {
            boolean dateLimiteDepassee = produit.getDateLimiteGroupage() != null &&
                    produit.getDateLimiteGroupage().isBefore(maintenant);

            boolean seuilAtteint = produit.getSeuilMinimum() != null &&
                    produit.getQuantiteDisponible() != null &&
                    produit.getQuantiteDisponible() <= produit.getSeuilMinimum();

            if (seuilAtteint) {
                System.out.println("Seuil atteint pour le produit : " + produit.getNom());
                System.out.println("➡ Notifier grossiste ID: " + produit.getIdGrossiste());
          //  Implémenter l’appel au notification-service ou email
            }

            

            if (dateLimiteDepassee) {
                System.out.println("Date limite atteinte pour le produit : " + produit.getNom());
                // Notifier le grossiste qu’il peut choisir (accepter, supprimer, republier)
            }
        }
    }
}
