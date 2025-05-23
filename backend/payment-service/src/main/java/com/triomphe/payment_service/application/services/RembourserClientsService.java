package com.triomphe.payment_service.application.services;

import com.triomphe.payment_service.application.port.out.PaiementRepository;
import com.triomphe.payment_service.application.port.out.PortefeuillePort;
import com.triomphe.payment_service.domain.model.CommandeId;
import com.triomphe.payment_service.domain.model.Paiement;
import com.triomphe.payment_service.domain.model.StatutPaiement;
import com.triomphe.payment_service.domain.service.TransactionServiceDomaine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RembourserClientsService {

    private final PaiementRepository paiementRepository;
    private final PortefeuillePort portefeuillePort;
    private final TransactionServiceDomaine transactionService;

    public void rembourserTousLesClients(CommandeId commandeId) {
        List<Paiement> paiements = paiementRepository.findAllByCommandeId(commandeId);

        for (Paiement paiement : paiements) {
            portefeuillePort.rembourserClient(paiement.getClientId(), paiement.getMontant());
            transactionService.enregistrerRemboursement(
                    paiement.getClientId().getId(),
                    paiement.getMontant(),
                    commandeId.getId()
            );
            paiement.setStatut(StatutPaiement.REFUSE);
            paiementRepository.save(paiement);
        }

        System.out.println("Notification : tous les clients ont été remboursés pour la commande " + commandeId.getId());


    }
}
