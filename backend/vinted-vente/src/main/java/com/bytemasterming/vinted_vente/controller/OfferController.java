package com.bytemasterming.vinted_vente.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.util.List;

import com.bytemasterming.vinted_vente.model.Offer;
import com.bytemasterming.vinted_vente.model.OfferStatus;
import com.bytemasterming.vinted_vente.repository.OfferRepository;

@RestController
@RequestMapping("/api/vinted/offres")
public class OfferController {

    @Autowired
    private OfferRepository offerRepository;

    @PostMapping("/{productId}")
    public Offer faireOffre(@PathVariable String productId, @RequestBody Offer offre) {
        offre.setProductId(productId);
        offre.setOfferDate(LocalDateTime.now());
        offre.setStatus(OfferStatus.EN_ATTENTE);
        return offerRepository.save(offre);
    }

    @GetMapping("/produit/{productId}")
    public List<Offer> offresParProduit(@PathVariable String productId) {
        return offerRepository.findByProductId(productId);
    }

    @GetMapping("/acheteur/{buyerId}")
    public List<Offer> offresParAcheteur(@PathVariable String buyerId) {
        return offerRepository.findByBuyerId(buyerId);
    }

    @PutMapping("/{offerId}/statut")
    public Offer changerStatut(@PathVariable String offerId, @RequestParam OfferStatus statut) {
        Offer offre = offerRepository.findById(offerId).orElseThrow(() -> new RuntimeException("Offre introuvable"));
        offre.setStatus(statut);
        return offerRepository.save(offre);
    }
}
