package org.dani.ecommerce.repositories;

import org.dani.ecommerce.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<ProductModel,Long> {
}
