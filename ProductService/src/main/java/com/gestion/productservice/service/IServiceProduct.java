package com.gestion.productservice.service;


import com.gestion.productservice.entities.Product;

import java.util.List;

public interface IServiceProduct {
    Product addProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
}

