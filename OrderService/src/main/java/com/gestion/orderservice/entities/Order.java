package com.gestion.orderservice.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    private String status;

    private Double totalAmount;

    @ElementCollection
    private List<Long> productIds; // Store product IDs only

    @Version
    private int version;
}
