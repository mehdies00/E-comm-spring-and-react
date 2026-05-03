package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class CreditCardPayment extends Payment {
    private String cardNumber;
    private String expirationDate;

    public CreditCardPayment() {
    }

    public CreditCardPayment(String cardNumber, String expirationDate) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

}
