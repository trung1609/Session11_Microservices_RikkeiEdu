package com.trung.pharmacyservice.controller;

import com.trung.pharmacyservice.dto.SellRequest;
import com.trung.pharmacyservice.entity.Medicine;
import com.trung.pharmacyservice.event.OrderEvent;
import com.trung.pharmacyservice.service.PharmacyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/pharmacies")
@RequiredArgsConstructor
public class PharmacyController {
    private final PharmacyService pharmacyService;

    @PostMapping("/process-order")
    public String processOrder(@RequestParam Long productId) {
        return pharmacyService.processOrder(productId);
    }

    @PostMapping("/create-invoice")
    public String createInvoice() {
        return pharmacyService.createInvoice();
    }

    @GetMapping("/validate-insurance")
    public CompletableFuture<String> validateInsurance() {
        return pharmacyService.validateInsurance();
    }

    @PostMapping("/sell")
    public ResponseEntity<String> sellMedicine(@RequestBody SellRequest request) {
        OrderEvent event = new OrderEvent(
                UUID.randomUUID().toString(),
                request.getMedicineId(),
                request.getUserEmail(),
                request.getQuantity(),
                LocalDateTime.now()
        );

        pharmacyService.sendOrderEvent(event);
        return ResponseEntity.ok("Sell order processed successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable Long id) {
        Medicine medicine = pharmacyService.getMedicineById(id);
        return ResponseEntity.ok(medicine);
    }
}
