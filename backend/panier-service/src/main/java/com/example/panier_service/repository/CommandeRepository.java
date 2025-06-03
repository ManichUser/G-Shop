package com.example.panier_service.repository;
// package com.example.panier_service.repository;

import com.example.panier_service.model.Commande;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CommandeRepository extends MongoRepository<Commande, String> {

    List<Commande> findByUserId(String userId);
    List<Commande> findByProductId(String productId);
    
    // Somme des quantités pour un produit donné
    @Query(value = "{ 'productId': ?0 }", fields = "{ 'quantity': 1 }")
    List<Commande> findAllByProductId(String productId);


}
