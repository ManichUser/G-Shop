package com.example.panier_service.client;

import com.example.panier_service.dto.ProduitDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "produits-service", url = "http://localhost:8082") // ou sans l'URL si vous ne la voulez pas
public interface ProduitClient {

    @GetMapping("/api/produits/{id}")
    ProduitDTO getProduitById(@PathVariable("id") Long id); // <-- Changez String en Long ici
}