package com.gestion.orderservice.service;


import com.gestion.orderservice.client.ProductRestClient;
import com.gestion.orderservice.entities.Order;
import com.gestion.orderservice.model.Product;
import com.gestion.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceOrder implements IServiceOrder {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRestClient productRestClient;

    @Override
    public Order addOrder(Order order) {
        // Ensure Hibernate treats this as a new entity
        order.setId(null);

        // Validate product IDs
        List<Product> products = order.getProductIds().stream()
                .map(productRestClient::getProductById)
                .collect(Collectors.toList());

        // Calculate total amount
        double totalAmount = products.stream()
                .mapToDouble(Product::getPrice)
                .sum();

        order.setTotalAmount(totalAmount);

        // Save and return the order
        return orderRepository.save(order);
    }



    @Override
    public Order updateOrder(Long id, Order order) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
        existingOrder.setCustomerId(order.getCustomerId());
        existingOrder.setOrderDate(order.getOrderDate());
        existingOrder.setStatus(order.getStatus());
        existingOrder.setTotalAmount(order.getTotalAmount());
        existingOrder.setProductIds(order.getProductIds());
        return orderRepository.save(existingOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new RuntimeException("Order not found with ID: " + id);
        }
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
