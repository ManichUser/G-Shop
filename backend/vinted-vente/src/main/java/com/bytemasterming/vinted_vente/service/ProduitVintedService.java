package com.bytemasterming.vinted_vente.service;

import com.bytemasterming.vinted_vente.model.ProductVinted;
import com.bytemasterming.vinted_vente.model.ProductStatus;
import com.bytemasterming.vinted_vente.model.Offer; // Importez la classe Offer

import java.util.List;

public interface ProduitVintedService {

    ProductVinted ajouterProduit(ProductVinted produit);

    List<ProductVinted> getProduitsDisponibles();

    List<ProductVinted> getProduitsParUtilisateur(String idUser);

    List<ProductVinted> getProduitsParUtilisateurEtStatut(String idUser, ProductStatus status);

    List<ProductVinted> rechercherProduitsParNom(String nomProduit);

    List<ProductVinted> getProduitsParCategorie(String categorie);

    List<ProductVinted> getTousLesProduits();

    Offer faireOffre(String productId, Offer offre);

    ProductVinted validerOffre(String productId, Long offerId);
}