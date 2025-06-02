package com.monapp.products_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monapp.products_service.model.Produit;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findByIdGrossiste(Long idGrossiste);
}