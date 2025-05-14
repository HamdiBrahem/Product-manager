package com.gestion.paymentservice.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;

    private Double amount;

    private String paymentMethod; // e.g., "CREDIT_CARD", "PAYPAL"

    private String status; // e.g., "SUCCESS", "PENDING", "FAILED"

    private LocalDateTime timestamp;
}
