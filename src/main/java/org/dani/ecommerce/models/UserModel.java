package org.dani.ecommerce.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;
    private String lastName;

    @Column(nullable = false,  unique = true)
    private String email;
    private String password;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String country;


}
