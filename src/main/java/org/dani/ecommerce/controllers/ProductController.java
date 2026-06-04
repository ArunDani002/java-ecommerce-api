package org.dani.ecommerce.controllers;


import org.dani.ecommerce.models.ProductModel;
import org.dani.ecommerce.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {

        private final ProductService productService;

        public ProductController(ProductService productService){
            this.productService = productService;
        }

    @PostMapping("/{sellerUuid}")
    public ProductModel createProduct(@RequestBody ProductModel productModel, @PathVariable String sellerUuid){
            return productService.createProduct(productModel, sellerUuid);
    }

    @GetMapping
    public List<ProductModel> getAllProducts(){
            return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductModel getProductById(@PathVariable Long id){
            return productService.getProductById(id);
    }

    @PutMapping("/{id}/{sellerUuid}")
    public ProductModel updateProduct(@RequestBody ProductModel productModel, @PathVariable Long id, String sellerUuid){
            return productService.updateProduct(id, productModel, sellerUuid);
    }

    @DeleteMapping("/{id}/{sellerUuid}")
    public ProductModel deleteProductById(@PathVariable Long id, String sellerUuid){
            return productService.deleteProduct(id, sellerUuid);
    }

}
