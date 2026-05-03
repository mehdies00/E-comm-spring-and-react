package com.example.demo.service;

import com.example.demo.model.Command;
import java.util.List;
import java.util.Optional;

public interface CommandService {
    List<Command> getAllCommands();

    Optional<Command> getCommandById(Integer id);

    Command saveCommand(Command command);

    Command updateCommand(Integer id, Command commandDetails);

    void deleteCommand(Integer id);
}