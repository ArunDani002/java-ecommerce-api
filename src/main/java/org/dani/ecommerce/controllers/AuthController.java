package org.dani.ecommerce.controllers;


import org.dani.ecommerce.dto.LoginRequest;
import org.dani.ecommerce.dto.LoginResponse;
import org.dani.ecommerce.services.LoginService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final LoginService loginService;

    public AuthController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return loginService.login(loginRequest);
    }

}
