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

    @PostMapping
    public ProductModel createProduct(@RequestBody ProductModel productModel){
            return productService.createProduct(productModel);
    }

    @GetMapping
    public List<ProductModel> getAllProducts(){
            return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductModel getProductById(@PathVariable Long id){
            return productService.getProductById(id);
    }

    @PutMapping
    public ProductModel updateProduct(@RequestBody ProductModel productModel, @PathVariable Long id){
            return productService.updateProduct(id, productModel);
    }

    @DeleteMapping("/{id}")
    public ProductModel deleteProductById(@PathVariable Long id){
            return productService.deleteProduct(id);
    }

}
