package com.gestion.orderservice.controller;

import com.gestion.orderservice.client.CustomerRestClient;
import com.gestion.orderservice.client.ProductRestClient;
import com.gestion.orderservice.entities.Order;
import com.gestion.orderservice.entities.OrderDetailsDTO;
import com.gestion.orderservice.model.Customer;
import com.gestion.orderservice.model.Product;
import com.gestion.orderservice.service.IServiceOrder;
import com.gestion.orderservice.service.ServiceOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderRestController {

    @Autowired
    private IServiceOrder serviceOrder;
    @Autowired
    private ProductRestClient productRestClient;

    @Autowired
    private CustomerRestClient customerRestClient;

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        return ResponseEntity.ok(serviceOrder.addOrder(order));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        return ResponseEntity.ok(serviceOrder.updateOrder(id, order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        serviceOrder.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<OrderDetailsDTO> getOrderDetails(@PathVariable Long id) {
        Order order = serviceOrder.getOrderById(id);
        List<Product> products = order.getProductIds().stream()
                .map(productRestClient::getProductById)
                .collect(Collectors.toList());

        OrderDetailsDTO orderDetails = new OrderDetailsDTO(order, products);
        return ResponseEntity.ok(orderDetails);
    }

    @GetMapping("/{orderId}/customer")
    public ResponseEntity<Customer> getCustomerForOrder(@PathVariable Long orderId) {
        Order order = serviceOrder.getOrderById(orderId);
        Customer customer = customerRestClient.getCustomerById(order.getCustomerId());
        return ResponseEntity.ok(customer);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(serviceOrder.getAllOrders());
    }

    @PutMapping("/api/orders/{id}/status")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        Order order = serviceOrder.getOrderById(id);
        order.setStatus(status);
        serviceOrder.updateOrder(id, order); // Ensure this method saves the updated order
        return ResponseEntity.noContent().build();
    }
}
