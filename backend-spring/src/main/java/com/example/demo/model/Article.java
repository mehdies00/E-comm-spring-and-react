package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idArticle;
    private String description;
    private String brand;
    private double price;

    public Article() {
    }

    public Article(String description, String brand, double price) {
        this.description = description;
        this.brand = brand;
        this.price = price;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public String getDescription() {
        return description;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
