package org.dani.ecommerce.controllers;

import org.dani.ecommerce.dto.CartRequest;
import org.dani.ecommerce.models.CartModel;
import org.dani.ecommerce.services.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin("*")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<CartModel> addToCart(
            @RequestBody CartRequest request,
            @RequestHeader("Authorization") String authHeader
    ) {
        CartModel savedCart = cartService.addToCart(request, authHeader);
        return ResponseEntity.ok(savedCart);
    }

    @GetMapping
    public ResponseEntity<List<CartModel>> getCart(
            @RequestHeader("Authorization") String authHeader) {

        return ResponseEntity.ok(cartService.getCart(authHeader));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return ResponseEntity.ok("Cart item deleted successfully");
    }
}