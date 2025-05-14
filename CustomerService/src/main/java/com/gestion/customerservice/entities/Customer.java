package com.gestion.customerservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    @Version
    private int version;

    // Fields for authentication
    private String username; // Unique username for login
    private String password; // Encrypted password

    @Enumerated(EnumType.STRING)
    private Role role; // Role can be ADMIN or USER

}

