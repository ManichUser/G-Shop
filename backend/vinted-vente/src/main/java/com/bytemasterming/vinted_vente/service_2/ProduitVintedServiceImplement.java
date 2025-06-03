package com.bytemasterming.vinted_vente.service;

import com.bytemasterming.vinted_vente.model.Offer;
import com.bytemasterming.vinted_vente.model.OfferStatus;
import com.bytemasterming.vinted_vente.model.ProductVinted;
import com.bytemasterming.vinted_vente.model.ProductStatus;
import com.bytemasterming.vinted_vente.repository.ProductVintedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProduitVintedServiceImplement implements ProduitVintedService {

    @Autowired
    private ProductVintedRepository productVintedRepository;

    @Override
    public ProductVinted ajouterProduit(ProductVinted produit) {
        produit.setStatus(ProductStatus.DISPONIBLE);
        return productVintedRepository.save(produit);
    }

    @Override
    public List<ProductVinted> getProduitsDisponibles() {
        return productVintedRepository.findByStatus(ProductStatus.DISPONIBLE);
    }

    @Override
    public List<ProductVinted> getProduitsParUtilisateur(String idUser) {
        return productVintedRepository.findByIdUser(idUser);
    }

    @Override
    public List<ProductVinted> getProduitsParUtilisateurEtStatut(String idUser, ProductStatus status) {
        return productVintedRepository.findByIdUserAndStatus(idUser, status);
    }

    @Override
    public List<ProductVinted> rechercherProduitsParNom(String nomProduit) {
        return productVintedRepository.findByProductNameContaining(nomProduit);
    }

    @Override
    public List<ProductVinted> getProduitsParCategorie(String categorie) {
        return productVintedRepository.findByCategory(categorie);
    }

    @Override
    public List<ProductVinted> getTousLesProduits() {
        return productVintedRepository.findAll();
    }

    @Override
    public Offer faireOffre(String productId, Offer offre) {
        Optional<ProductVinted> optionalProduct = productVintedRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            ProductVinted produit = optionalProduct.get();
            offre.setOfferDate(LocalDateTime.now());
            offre.setStatus(OfferStatus.EN_ATTENTE);
            offre.setProductId(productId);
            produit.getOffres().add(offre);
            productVintedRepository.save(produit);
            return offre;
        } else {
            throw new RuntimeException("Produit non trouvé avec ID: " + productId);
        }
    }

    @Override
    public ProductVinted validerOffre(String productId, Long offerId) {
        Optional<ProductVinted> optionalProduct = productVintedRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            ProductVinted produit = optionalProduct.get();
            boolean offreTrouvee = false;
            for (Offer offre : produit.getOffres()) {
                if (offre.getId().equals(offerId)) {
                    offre.setStatus(OfferStatus.ACCEPTEE);
                    produit.setStatus(ProductStatus.INDISPONIBLE);
                    offreTrouvee = true;
                } else {
                    offre.setStatus(OfferStatus.REFUSEE); // autres offres refusées
                }
            }
            if (!offreTrouvee) {
                throw new RuntimeException("Offre introuvable pour ce produit.");
            }
            return productVintedRepository.save(produit);
        } else {
            throw new RuntimeException("Produit non trouvé avec ID: " + productId);
        }

    }

}