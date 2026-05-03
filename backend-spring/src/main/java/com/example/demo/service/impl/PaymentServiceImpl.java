package com.example.demo.service.impl;

import com.example.demo.model.Payment;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Optional<Payment> getPaymentById(int id) {
        return paymentRepository.findById(id);
    }

    @Override
    @Transactional
    public Payment savePayment(Payment payment) {
        if (payment.getAmount() == null || payment.getAmount() <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero");
        }
        if (payment.getPaymentDate() == null) {
            throw new IllegalArgumentException("Payment date cannot be null");
        }
        if (payment.getCommand() == null) {
            throw new IllegalArgumentException("Payment must be associated with a command");
        }
        return paymentRepository.save(payment);
    }

    @Override
    @Transactional
    public Payment updatePayment(int id, Payment paymentDetails) {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found for ID: " + id));

        if (paymentDetails.getAmount() == null || paymentDetails.getAmount() <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero");
        }
        if (paymentDetails.getPaymentDate() == null) {
            throw new IllegalArgumentException("Payment date cannot be null");
        }
        if (paymentDetails.getCommand() == null) {
            throw new IllegalArgumentException("Payment must be associated with a command");
        }

        existingPayment.setAmount(paymentDetails.getAmount());
        existingPayment.setPaymentDate(paymentDetails.getPaymentDate());
        existingPayment.setCommand(paymentDetails.getCommand());

        return paymentRepository.save(existingPayment);
    }

    @Override
    @Transactional
    public void deletePayment(int id) {
        if (!paymentRepository.existsById(id)) {
            throw new RuntimeException("Payment not found for ID: " + id);
        }
        paymentRepository.deleteById(id);
    }
}