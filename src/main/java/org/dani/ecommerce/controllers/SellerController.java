package org.dani.ecommerce.controllers;


import org.dani.ecommerce.services.SellerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seller")
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("/test")
    public String test() {
        return "Seller Controller Working";
    }

    @PatchMapping("/become/{uuid}")
    public String becomeSeller(@PathVariable String uuid){
        sellerService.becomeSeller(uuid);
        return "User upgraded to SELLER";
    }
}
