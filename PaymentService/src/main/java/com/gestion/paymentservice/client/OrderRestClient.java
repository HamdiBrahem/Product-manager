package com.gestion.paymentservice.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(name = "order-service", url = "http://localhost:8084")
public interface OrderRestClient {
    @PutMapping("/api/orders/{id}/status")
    void updateOrderStatus(@PathVariable Long id, @RequestParam String status);
}
