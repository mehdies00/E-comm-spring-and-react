package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Command {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCommand;

    private Date commandDate;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    public Command() {
    }

    public Command(Date commandDate, User user) {
        this.commandDate = commandDate;
        this.user = user;
    }

    public int getIdCommand() {
        return idCommand;
    }

    public Date getCommandDate() {
        return commandDate;
    }

    public User getUser() {
        return user;
    }

    public void setIdCommand(int idCommand) {
        this.idCommand = idCommand;
    }

    public void setCommandDate(Date commandDate) {
        this.commandDate = commandDate;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
