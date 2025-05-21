package com.bytemasterming.repository;

import com.bytemasterming.model.ProductVinted;
import com.bytemasterming.model.ProductStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVintedRepository extends MongoRepository<ProductVinted, String> {

    List<ProductVinted> findByIdUser(String idUser); //produits de l'utilisateur

    List<ProductVinted> findByCategory(String category); //produits d'une catégorie

    List<ProductVinted> findByStatus(ProductStatus status); //produits d'un statut

    List<ProductVinted> findByProductNameContaining(String productName); //produits d'un nom

    List<ProductVinted> findByIdUserAndStatus(String idUser, ProductStatus status); //produits d'un utilisateur et d'un statut

}
