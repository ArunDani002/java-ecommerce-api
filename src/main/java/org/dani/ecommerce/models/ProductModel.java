package org.dani.ecommerce.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "products")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private String productDescription;
    @Column(nullable = false)
    private Double productPrice;
    @Column(nullable = false)
    private String productCategory;
    @Column(nullable = false)
    private String productImageUrl;
    @Column(nullable = false)
    private String sellerUuid;
    @Column(nullable = false)
    private String stocks;
    private LocalDateTime createdAt;




}
