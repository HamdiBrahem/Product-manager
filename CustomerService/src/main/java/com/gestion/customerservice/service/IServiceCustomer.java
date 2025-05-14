package com.gestion.customerservice.service;


import com.gestion.customerservice.entities.Customer;

import java.util.List;

public interface IServiceCustomer {
    Customer addCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer customer);
    void deleteCustomer(Long id);
    Customer getCustomerById(Long id);
    List<Customer> getAllCustomers();
    Customer getCustomerByEmail(String email);
}