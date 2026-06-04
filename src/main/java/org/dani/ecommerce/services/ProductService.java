package org.dani.ecommerce.services;

import org.dani.ecommerce.enums.Role;
import org.dani.ecommerce.models.ProductModel;
import org.dani.ecommerce.models.UserModel;
import org.dani.ecommerce.repositories.ProductRepo;
import org.dani.ecommerce.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;


@Service
public class ProductService {

    private ProductRepo productRepo;
    private final UserRepository userRepository;

    public ProductService(UserRepository userRepository, ProductRepo productRepo) {
        this.userRepository = userRepository;
        this.productRepo = productRepo;
    }


    public ProductModel createProduct(ProductModel productModel, String sellerUuid) {

        UserModel user = userRepository.findByUuid(sellerUuid)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole() != Role.SELLER) {
            throw new RuntimeException("Only SELLER can create products");
        }


        productModel.setSellerUuid(user.getUuid());
        return productRepo.save(productModel);
    }

    public List<ProductModel> getAllProducts(){
        return productRepo.findAll();
    }

    public ProductModel updateProduct (Long id, ProductModel productModel, String sellerUuid) {
        ProductModel existProduct = productRepo.findById(id)
                .orElseThrow(()->new RuntimeException("product not found"));

        if (!existProduct.getSellerUuid().equals(sellerUuid)) {
            throw new RuntimeException("You are not allowed to update this product");
        }

        existProduct.setProductName(productModel.getProductName());
        existProduct.setProductCategory(productModel.getProductCategory());
        existProduct.setProductPrice(productModel.getProductPrice());
        existProduct.setProductDescription(productModel.getProductDescription());
        existProduct.setProductImageUrl(productModel.getProductImageUrl());

        return productRepo.save(existProduct);
    }

    public ProductModel getProductById(Long id){
        return productRepo.findById(id)
                .orElseThrow(()->new RuntimeException("product not found" + id));
    }

    public ProductModel deleteProduct(Long id, String sellerUuid){

        ProductModel product =  productRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("product not found" + id));

        if (!product.getSellerUuid().equals(sellerUuid)) {
            throw new RuntimeException("You are not allowed to delete this product");
        }

        productRepo.delete(product);
        return product;
    }

}
