
package com.monapp.products_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.monapp.products_service.model.Produit;

import lombok.Data;

@Data
public class ProduitRequestDTO {
    private String nom;
    private String description;
    private BigDecimal prixUnitaire;
    private Integer seuilMinimum;
    private Integer quantiteDisponible;
    private LocalDateTime dateLimiteGroupage;
    private String image;
    private Long idGrossiste;


    public Produit toEntity() {
        Produit produit = new Produit();
        produit.setNom(nom);
        produit.setDescription(description);
        produit.setPrixUnitaire(prixUnitaire);
        produit.setSeuilMinimum(seuilMinimum);
        produit.setQuantiteDisponible(quantiteDisponible);
        produit.setDateLimiteGroupage(dateLimiteGroupage);
        produit.setImage(image);
        produit.setIdGrossiste(idGrossiste);
        return produit;
    }
}
