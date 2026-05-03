package com.example.demo.service;

import com.example.demo.model.Payment;
import java.util.List;
import java.util.Optional;

public interface PaymentService {
    List<Payment> getAllPayments();

    Optional<Payment> getPaymentById(int id);

    Payment savePayment(Payment payment);

    Payment updatePayment(int id, Payment paymentDetails);

    void deletePayment(int id);
}