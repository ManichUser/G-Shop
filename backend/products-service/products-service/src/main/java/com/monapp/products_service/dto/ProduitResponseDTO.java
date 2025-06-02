package com.monapp.products_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.monapp.products_service.model.Produit;

import lombok.Data;

@Data
public class ProduitResponseDTO {
    private Long id;
    private String nom;
    private String description;
    private BigDecimal prixUnitaire;
    private Integer seuilMinimum;
    private Integer quantiteDisponible;
    private LocalDateTime dateLimiteGroupage;
    private Long idGrossiste;
    private String image;
    private LocalDateTime dateCreation;

    private boolean seuilAtteint;
    private boolean dateLimiteDepassee;

    public static ProduitResponseDTO fromEntity(Produit produit) {
        ProduitResponseDTO dto = new ProduitResponseDTO();
        dto.setId(produit.getId());
        dto.setNom(produit.getNom());
        dto.setDescription(produit.getDescription());
        dto.setPrixUnitaire(produit.getPrixUnitaire());
        dto.setSeuilMinimum(produit.getSeuilMinimum());
        dto.setQuantiteDisponible(produit.getQuantiteDisponible());
        dto.setDateLimiteGroupage(produit.getDateLimiteGroupage());
        dto.setImage(produit.getImage());
        dto.setDateCreation(produit.getDateCreation());
        dto.setIdGrossiste(produit.getIdGrossiste());


        dto.setSeuilAtteint(
            produit.getQuantiteDisponible() != null &&
            produit.getSeuilMinimum() != null &&
            produit.getQuantiteDisponible() <= produit.getSeuilMinimum()
        );
        dto.setDateLimiteDepassee(
            produit.getDateLimiteGroupage() != null &&
            produit.getDateLimiteGroupage().isBefore(LocalDateTime.now())
        );

        return dto;
    }
}
