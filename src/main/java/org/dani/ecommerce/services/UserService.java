package org.dani.ecommerce.services;

import org.dani.ecommerce.models.UserModel;
import org.dani.ecommerce.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserModel createUser(UserModel userModel) {
        String hashedPassword = passwordEncoder.encode(userModel.getPassword());
        userModel.setPassword(hashedPassword);
        return userRepository.save(userModel);
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

//    public UserModel getUserById(Long id) {
//        return userRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
//    }

    public UserModel getUserByUuid(String uuid) {
        return userRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserModel updateUser(Long id, UserModel userModel) {
        UserModel existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id" + id));
        existingUser.setFirstName(userModel.getFirstName());
        existingUser.setLastName(userModel.getLastName());
        existingUser.setEmail(userModel.getEmail());
        existingUser.setCountry(userModel.getCountry());
        existingUser.setCity(userModel.getCity());
        existingUser.setState(userModel.getState());

        // Only hash & update password if it is changed
        if (userModel.getPassword() != null && !userModel.getPassword().isBlank()) {
            String hashedPassword = passwordEncoder.encode(userModel.getPassword());
            existingUser.setPassword(hashedPassword);
        }


        return userRepository.save(existingUser);
    }


}
