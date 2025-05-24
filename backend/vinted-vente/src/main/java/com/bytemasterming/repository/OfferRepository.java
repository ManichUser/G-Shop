package com.bytemasterming.repository;

import com.bytemasterming.model.Offer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends MongoRepository<Offer, String> {

    List<Offer> findByProductId(String productId); // la liste des offres  d'un produit
    List<Offer> findByBuyerId(String buyerId); // la liste des offres d'un acheteur
    List<Offer> findByProductIdAndBuyerId(String productId, String buyerId); // la liste des offres d'un acheteur sur un produit
    
}
