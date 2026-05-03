package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idArticle;
    private String description;
    private String brand;
    private Double price;
    private String imageUrl;
    private String name;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommandLine> commandLines;

    public Article() {
    }

    public Article(String description, String brand, Double price, String imageUrl, String name,
            List<CommandLine> commandLines) {
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.imageUrl = imageUrl;
        this.name = name;
        this.commandLines = commandLines;
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

    public Double getPrice() {
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

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<CommandLine> getCommandLines() {
        return commandLines;
    }

    public void setCommandLines(List<CommandLine> commandLines) {
        this.commandLines = commandLines;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
