package org.dani.ecommerce.services;

import org.dani.ecommerce.dto.CartRequest;
import org.dani.ecommerce.models.CartModel;
import org.dani.ecommerce.models.ProductModel;
import org.dani.ecommerce.models.UserModel;
import org.dani.ecommerce.repositories.CartRepo;
import org.dani.ecommerce.repositories.ProductRepo;
import org.dani.ecommerce.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepo cartRepo;
    private final ProductRepo productRepo;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public CartService(
            CartRepo cartRepo,
            ProductRepo productRepo,
            UserRepository userRepository,
            JwtService jwtService
    ) {
        this.cartRepo = cartRepo;
        this.productRepo = productRepo;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public CartModel addToCart(CartRequest request, String authHeader) {

        String token = authHeader.substring(7);
        String email = jwtService.extractUsername(token);
        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ProductModel product = productRepo.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartModel cart = new CartModel();
        cart.setUser(user);
        cart.setProduct(product);
        cart.setQuantity(request.getQuantity());

        return cartRepo.save(cart);
    }

    public List<CartModel> getCart(String authHeader) {

        String token = authHeader.substring(7);

        String email = jwtService.extractUsername(token);

        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return cartRepo.findByUser(user);
    }

    public void deleteCart(Long id) {
        CartModel cart = cartRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        cartRepo.delete(cart);
    }
}