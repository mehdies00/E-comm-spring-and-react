package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPayment;
    private Double amount;
    private LocalDateTime paymentDate;
    @OneToOne
    @JoinColumn(name = "idCommand")
    private Command command;

    public Payment() {
    }

    public Payment(Double amount, LocalDateTime paymentDate, Command command) {
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.command = command;
    }

    public int getIdPayment() {
        return idPayment;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public Command getCommand() {
        return command;
    }

    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

}
