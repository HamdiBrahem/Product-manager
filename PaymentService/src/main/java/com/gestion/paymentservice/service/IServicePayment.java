package com.gestion.paymentservice.service;
import com.gestion.paymentservice.entities.Payment;

import java.util.List;
public interface IServicePayment {
    Payment createPayment(Payment payment);

    Payment getPaymentById(Long id);

    List<Payment> getPayments();

    Payment updatePaymentStatus(Long id, String status);
}
