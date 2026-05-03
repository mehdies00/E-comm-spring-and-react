package com.example.demo.service.impl;

import com.example.demo.model.Command;
import com.example.demo.repository.CommandRepository;
import com.example.demo.service.CommandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CommandServiceImpl implements CommandService {

    private final CommandRepository commandRepository;

    public CommandServiceImpl(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    @Override
    public List<Command> getAllCommands() {
        return commandRepository.findAll();
    }

    @Override
    public Optional<Command> getCommandById(Integer id) {
        return commandRepository.findById(id);
    }

    @Override
    @Transactional
    public Command saveCommand(Command command) {
        if (command.getCommandDate() == null) {
            throw new IllegalArgumentException("Command date cannot be null");
        }
        if (command.getUser() == null) {
            throw new IllegalArgumentException("User must be associated with the command");
        }
        return commandRepository.save(command);
    }

    @Override
    @Transactional
    public Command updateCommand(Integer id, Command commandDetails) {
        Command existingCommand = commandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Command not found for ID: " + id));

        if (commandDetails.getCommandDate() == null) {
            throw new IllegalArgumentException("Command date cannot be null");
        }
        if (commandDetails.getUser() == null) {
            throw new IllegalArgumentException("User must be associated with the command");
        }

        existingCommand.setCommandDate(commandDetails.getCommandDate());
        existingCommand.setUser(commandDetails.getUser());

        return commandRepository.save(existingCommand);
    }

    @Override
    @Transactional
    public void deleteCommand(Integer id) {
        if (!commandRepository.existsById(id)) {
            throw new RuntimeException("Command not found for ID: " + id);
        }
        commandRepository.deleteById(id);
    }
}