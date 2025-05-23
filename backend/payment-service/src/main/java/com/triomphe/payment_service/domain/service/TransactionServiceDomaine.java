package com.triomphe.payment_service.domain.service;

import com.triomphe.payment_service.application.port.out.TransactionRepository;
import com.triomphe.payment_service.domain.model.Paiement;
import com.triomphe.payment_service.domain.model.TransactionPaiement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@Service
@RequiredArgsConstructor
public class TransactionServiceDomaine {


    public void enregistrerPaiementFournisseur (Paiement paiement,double commission){
    }

    public void enregistrerRemboursement (String id, Double montant){
    }

    private final TransactionRepository transactionRepository;

    public void enregistrerRemboursement(String clientId, double montant, String commandeId) {
        TransactionPaiement transaction = TransactionPaiement.builder()
                .type("REMBOURSEMENT")
                .clientId(clientId)
                .commandeId(commandeId)
                .montant(montant)
                .date(LocalDateTime.now())
                .build();

        transactionRepository.save(transaction);


    }
}


