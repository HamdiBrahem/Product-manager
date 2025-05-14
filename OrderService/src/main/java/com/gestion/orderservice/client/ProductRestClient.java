package com.gestion.orderservice.client;
import com.gestion.orderservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@FeignClient(name = "product-service", url = "http://localhost:8084")
public interface ProductRestClient {
    @GetMapping("/api/products/{id}")
    Product getProductById(@PathVariable Long id);

    @GetMapping("/api/products")
    List<Product> getAllProducts();
}
