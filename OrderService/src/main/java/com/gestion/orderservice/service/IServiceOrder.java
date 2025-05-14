package com.gestion.orderservice.service;

import com.gestion.orderservice.entities.Order;

import java.util.List;

public interface IServiceOrder {
    Order addOrder(Order order);
    Order updateOrder(Long id, Order order);
    void deleteOrder(Long id);
    Order getOrderById(Long id);
    List<Order> getAllOrders();
}
