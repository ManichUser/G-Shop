package com.triomphe.payment_service.application.services;

import com.triomphe.payment_service.application.dto.PaiementRequest;
import com.triomphe.payment_service.application.dto.PaiementResponse;
import com.triomphe.payment_service.application.port.in.PaiementUseCase;
import com.triomphe.payment_service.application.port.out.ApiPaiementExternePort;
import com.triomphe.payment_service.application.port.out.PaiementRepository;
import com.triomphe.payment_service.domain.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DemarrerPaiementService implements PaiementUseCase {

    private final PaiementRepository paiementRepository;
    private final ApiPaiementExternePort apiPaiementExternePort;

    @Override
    public PaiementResponse demarrerPaiement(PaiementRequest request) {

        // 1. Exécuter le paiement via PawaPay
        boolean succes = apiPaiementExternePort.executerPaiement(
                request.getClientId(),
                request.getMontant(),
                request.getModePaiement()
        );

        if (!succes) {
            throw new RuntimeException("❌ Paiement échoué via PawaPay");
        }

        // 2. Construire l'objet Paiement
        Paiement paiement = Paiement.builder()
                .commandeId(new CommandeId(request.getCommandeId()))
                .clientId(new ClientId(request.getClientId()))
                .montant(request.getMontant())
                .modePaiement(request.getModePaiement())
                .statut(StatutPaiement.EN_ATTENTE)
                .dateCreation(LocalDateTime.now())
                .dateExpiration(LocalDateTime.now().plusDays(2))
                .build();

        // 3. Sauvegarder en base
        Paiement sauvegarde = paiementRepository.save(paiement);

        // 4. Retourner la réponse
        return new PaiementResponse(
                sauvegarde.getId(),
                sauvegarde.getStatut(),
                sauvegarde.getMontant(),
                sauvegarde.getModePaiement()
        );
    }
}
