package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class CommandLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCommandLine;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "id_command")
    private Command command;
    @ManyToOne
    @JoinColumn(name = "id_article")
    private Article article;

    public CommandLine() {
    }

    public CommandLine(Integer quantity, Command command, Article article) {
        this.quantity = quantity;
        this.command = command;
        this.article = article;
    }

    public int getIdCommandLine() {
        return idCommandLine;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Command getCommand() {
        return command;
    }

    public Article getArticle() {
        return article;
    }

    public void setIdCommandLine(int idCommandLine) {
        this.idCommandLine = idCommandLine;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

}
