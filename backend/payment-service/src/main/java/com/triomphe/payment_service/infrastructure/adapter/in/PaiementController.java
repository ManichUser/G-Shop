
package com.triomphe.payment_service.infrastructure.adapter.in;

import com.triomphe.payment_service.application.dto.PaiementRequest;
import com.triomphe.payment_service.application.dto.PaiementResponse;
import com.triomphe.payment_service.application.services.AnnulerPaiementService;
import com.triomphe.payment_service.application.services.DemarrerPaiementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paiements")
@RequiredArgsConstructor
public class PaiementController {

    private final DemarrerPaiementService demarrerPaiementService;
    private final AnnulerPaiementService annulerPaiementService;


    @PostMapping("/demarrer")
    public ResponseEntity<PaiementResponse> demarrer(@RequestBody PaiementRequest request) {
        PaiementResponse response = demarrerPaiementService.demarrerPaiement(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/annuler/{paiementId}")
    public ResponseEntity<String> annulerPaiement(@PathVariable Long paiementId) {
        annulerPaiementService.annulerPaiement(paiementId);
        return ResponseEntity.ok("✅ Paiement annulé avec succès !");
    }

}
