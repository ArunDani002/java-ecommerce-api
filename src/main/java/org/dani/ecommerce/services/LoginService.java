package org.dani.ecommerce.services;

import org.dani.ecommerce.dto.LoginRequest;
import org.dani.ecommerce.dto.LoginResponse;
import org.dani.ecommerce.models.UserModel;
import org.dani.ecommerce.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder ;
    private final JwtService jwtService;

    public LoginService(UserRepository userRepository,
                        BCryptPasswordEncoder passwordEncoder,
                        JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        UserModel user = userRepository.findByEmail(loginRequest.getUsername())
                .orElseThrow(()-> new RuntimeException("Invalid username"));

        boolean isPasswordValid = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
        if(!isPasswordValid) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(loginRequest.getUsername());

        return new LoginResponse(
                "Login Successful" + user.getEmail(),
                true,
                token,
                user.getRole().name(),
                user.getUuid(),
                user.getFirstName()
        );
    }

}
