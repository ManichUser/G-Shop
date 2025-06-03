package com.example.panier_service.dto;


import lombok.Data;

@Data
public class ProduitDTO {
    private String id;
    private String nom;
    private int seuilGroupage;
    private int prix; // facultatif, utile si tu veux l'afficher ou le valider
}

