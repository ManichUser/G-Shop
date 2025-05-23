package com.triomphe.payment_service.infrastructure.adapter.in;

import com.triomphe.payment_service.application.services.RembourserClientsService;

import com.triomphe.payment_service.domain.model.CommandeId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/remboursements")
@RequiredArgsConstructor
public class RemboursementController {

    private final RembourserClientsService rembourserClientsService;

    @PostMapping("/{commandeId}")
    public ResponseEntity<String> rembourser(@PathVariable String commandeId) {
        rembourserClientsService.rembourserTousLesClients(new CommandeId(commandeId));
        return ResponseEntity.ok("✅ Remboursement effectué pour la commande " + commandeId);
    }
}
