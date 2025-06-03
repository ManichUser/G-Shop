package com.example.panier_service.client;


import com.example.panier_service.dto.ProduitDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "panier-service", url = "http://localhost:8082")
public interface ProduitClient {

    @GetMapping("/api/produits/{id}")
    ProduitDTO getProduitById(@PathVariable("id") String id);
}

