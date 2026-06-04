package org.dani.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String email;
    private boolean success;
    private String token;
    private String role;
    private String uuid;
    private String firstName;

}
