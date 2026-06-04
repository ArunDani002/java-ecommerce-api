package org.dani.ecommerce.services;

import jakarta.transaction.Transactional;
import org.dani.ecommerce.enums.Role;
import org.dani.ecommerce.models.UserModel;
import org.dani.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    private final UserRepository userRepository;

    public SellerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void becomeSeller(String uuid){
        UserModel user = userRepository.findByUuid(uuid)
                .orElseThrow(()->new RuntimeException("User not found"));

        if(user.getRole() != Role.CUSTOMER){
            throw new RuntimeException("Only Customers are supported");
        }

        user.setRole(Role.SELLER);
    }

}
