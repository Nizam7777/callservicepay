package org.example.gudilin.callservicepay.controllers;

import org.example.gudilin.callservicepay.Model.Purchase;
import org.example.gudilin.callservicepay.Repositories.PurchaseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseControllers {
    private final PurchaseRepository purchaseRepository;

    public PurchaseControllers(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @PostMapping()
    public void storePurchase(
            @RequestBody Purchase purchase
    ){
        purchaseRepository.storePurchase(purchase);
    }

    @GetMapping()
    public List<Purchase> getAllPurchase(){
        return purchaseRepository.findAllPurchaces();
    }
}
