package org.dani.ecommerce.models;

import jakarta.persistence.*;
import lombok.Data;
import org.dani.ecommerce.enums.Role;

import java.util.UUID;

@Entity
@Data
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String uuid;

    @Column(nullable = false)
    private String firstName;
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String country;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    

    @PrePersist
    public void onCreate() {
        this.uuid = UUID.randomUUID().toString();
        if (this.role == null) {
            this.role = Role.CUSTOMER;
        }
    }


}
