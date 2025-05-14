package com.gestion.orderservice.entities;

import com.gestion.orderservice.entities.Order;
import com.gestion.orderservice.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderDetailsDTO {
    private Order order;
    private List<Product> products;
}