package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class PaypalPayment extends Payment {
    private String paypalEmail;

    public PaypalPayment() {
    }

    public PaypalPayment(String paypalEmail) {
        this.paypalEmail = paypalEmail;
    }

    public String getPaypalEmail() {
        return paypalEmail;
    }

    public void setPaypalEmail(String paypalEmail) {
        this.paypalEmail = paypalEmail;
    }

}
