package org.dani.ecommerce.repositories;

import org.dani.ecommerce.models.CartModel;
import org.dani.ecommerce.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepo extends JpaRepository<CartModel, Long> {

    List<CartModel> findByUser(UserModel user);

}
