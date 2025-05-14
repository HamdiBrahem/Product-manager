package com.gestion.orderservice.client;

import com.gestion.orderservice.configuration.FeignConfig;
import com.gestion.orderservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", url = "http://localhost:8081", configuration = FeignConfig.class)
public interface CustomerRestClient {

    @GetMapping("/api/customers/{id}")
    Customer getCustomerById(@PathVariable Long id);
}
