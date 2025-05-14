package com.gestion.paymentservice.service;
import com.gestion.paymentservice.client.OrderRestClient;
import com.gestion.paymentservice.entities.Payment;
import com.gestion.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServicePayment implements IServicePayment {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRestClient orderClient; // Feign client for order service
    @Autowired
    private OrderRestClient orderRestClient;

    @Override
    public Payment createPayment(Payment payment) {
        payment.setId(null); // Ensure new payment
        payment.setTimestamp(LocalDateTime.now());
        payment.setStatus("PENDING"); // Default status
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with ID: " + id));
    }

    @Override
    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment updatePaymentStatus(Long id, String status) {
        Payment payment = getPaymentById(id);
        payment.setStatus(status);

        // Notify OrderService about payment status change
        if ("SUCCESS".equalsIgnoreCase(status)) {
            orderRestClient.updateOrderStatus(payment.getOrderId(), "PAID");
        } else if ("FAILED".equalsIgnoreCase(status)) {
            orderRestClient.updateOrderStatus(payment.getOrderId(), "PAYMENT_FAILED");
        }

        return paymentRepository.save(payment);
    }
}
