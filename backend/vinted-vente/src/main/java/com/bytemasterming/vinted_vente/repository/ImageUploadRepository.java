package com.bytemasterming.vinted_vente.repository;

import com.bytemasterming.vinted_vente.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByProduct_Id(String productId); // Récupère les images d’un produit
}
