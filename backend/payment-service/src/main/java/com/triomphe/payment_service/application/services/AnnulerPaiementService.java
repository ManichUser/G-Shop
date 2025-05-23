package com.triomphe.payment_service.application.services;

import com.triomphe.payment_service.application.port.out.PaiementRepository;
import com.triomphe.payment_service.application.port.out.PortefeuillePort;
import com.triomphe.payment_service.domain.model.Paiement;
import com.triomphe.payment_service.domain.model.StatutPaiement;
import com.triomphe.payment_service.domain.service.TransactionServiceDomaine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnnulerPaiementService {

    private final PaiementRepository paiementRepository;
    private final PortefeuillePort portefeuillePort;
    private final TransactionServiceDomaine transactionService;

    public void annulerPaiement(Long paiementId) {
        Paiement paiement = paiementRepository.findById(paiementId)
                .orElseThrow(() -> new RuntimeException("Paiement introuvable"));

        if (paiement.getStatut() != StatutPaiement.EN_ATTENTE) {
            throw new IllegalStateException("Le paiement ne peut être annulé car son statut est " + paiement.getStatut());

        }

        portefeuillePort.rembourserClient(paiement.getClientId(), paiement.getMontant());

        transactionService.enregistrerRemboursement(
                paiement.getClientId().getId(),
                paiement.getMontant(),
                paiement.getCommandeId().getId()
        );

        paiement.setStatut(StatutPaiement.ANNULER);
        paiementRepository.save(paiement);
    }
}
