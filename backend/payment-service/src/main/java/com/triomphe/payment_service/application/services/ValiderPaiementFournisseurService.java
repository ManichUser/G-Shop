package com.triomphe.payment_service.application.services;

import com.triomphe.payment_service.application.dto.ValidationPaiementDTO;
import com.triomphe.payment_service.application.port.in.ValidationPaiementFournisseurUseCase;
import com.triomphe.payment_service.application.port.out.*;
import com.triomphe.payment_service.domain.model.Paiement;
import com.triomphe.payment_service.domain.model.StatutPaiement;
import com.triomphe.payment_service.domain.service.TransactionServiceDomaine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ValiderPaiementFournisseurService implements ValidationPaiementFournisseurUseCase {


    private final PaiementRepository paiementRepository;
    private final PortefeuillePort portefeuillePort;
    private final TransactionServiceDomaine transactionService;

    @Override
    public void validerPaiement(ValidationPaiementDTO dto) {
        Paiement paiement = paiementRepository.findById(dto.getPaiementId())
                .orElseThrow(() -> new RuntimeException("Paiement introuvable"));

        if (dto.isAccepte()) {
            // Calcul des montants
            double montant = paiement.getMontant();
            double commission = montant * 0.02;
            double netFournisseur = montant - commission;

            // Débit compte plateforme (hypothèse : déjà stocké)
            portefeuillePort.debiterComptePlateforme(montant);

            // Crédit compte fournisseur
            portefeuillePort.crediterCompteFournisseur(paiement.getCommandeId(), netFournisseur);

            // Enregistre la transaction
            transactionService.enregistrerPaiementFournisseur(paiement, commission);

            // Met à jour le statut
            paiement.setStatut(StatutPaiement.VALIDE);
        } else {
            paiement.setStatut(StatutPaiement.REFUSE);

        }

        paiementRepository.save(paiement);
    }
}
