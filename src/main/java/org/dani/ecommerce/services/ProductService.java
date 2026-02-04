package org.dani.ecommerce.services;

import org.dani.ecommerce.models.ProductModel;
import org.dani.ecommerce.repositories.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;


@Service
public class ProductService {

    private ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public ProductModel createProduct(ProductModel productModel){
        return productRepo.save(productModel);
    }

    public List<ProductModel> getAllProducts(){
        return productRepo.findAll();
    }

    public ProductModel updateProduct (Long id, ProductModel productModel){
        ProductModel existProduct = productRepo.findById(id)
                .orElseThrow(()->new RuntimeException("product not found"));

        existProduct.setProductName(productModel.getProductName());
        existProduct.setProductCategory(productModel.getProductCategory());
        existProduct.setProductPrice(productModel.getProductPrice());
        existProduct.setProductDescription(productModel.getProductDescription());

        return productRepo.save(existProduct);
    }

    public ProductModel getProductById(Long id){
        return productRepo.findById(id)
                .orElseThrow(()->new RuntimeException("product not found" + id));
    }

    public ProductModel deleteProduct(Long id){
        ProductModel product =  productRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("product not found" + id));

        productRepo.delete(product);
        return product;
    }

}
