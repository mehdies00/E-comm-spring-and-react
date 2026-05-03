package com.example.demo.service.impl;

import com.example.demo.model.CommandLine;
import com.example.demo.repository.CommandLineRepository;
import com.example.demo.service.CommandLineService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CommandLineServiceImpl implements CommandLineService {

    private final CommandLineRepository commandLineRepository;

    public CommandLineServiceImpl(CommandLineRepository commandLineRepository) {
        this.commandLineRepository = commandLineRepository;
    }

    @Override
    public List<CommandLine> getAllCommandLines() {
        return commandLineRepository.findAll();
    }

    @Override
    public Optional<CommandLine> getCommandLineById(Integer id) {
        return commandLineRepository.findById(id);
    }

    @Override
    @Transactional
    public CommandLine saveCommandLine(CommandLine commandLine) {
        if (commandLine.getQuantity() == null || commandLine.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        if (commandLine.getCommand() == null) {
            throw new IllegalArgumentException("Command must be associated with the command line");
        }
        if (commandLine.getArticle() == null) {
            throw new IllegalArgumentException("Article must be associated with the command line");
        }
        return commandLineRepository.save(commandLine);
    }

    @Override
    @Transactional
    public CommandLine updateCommandLine(Integer id, CommandLine commandLineDetails) {
        CommandLine existingLine = commandLineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CommandLine not found for ID: " + id));

        if (commandLineDetails.getQuantity() == null || commandLineDetails.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        if (commandLineDetails.getCommand() == null) {
            throw new IllegalArgumentException("Command must be associated with the command line");
        }
        if (commandLineDetails.getArticle() == null) {
            throw new IllegalArgumentException("Article must be associated with the command line");
        }

        existingLine.setQuantity(commandLineDetails.getQuantity());
        existingLine.setCommand(commandLineDetails.getCommand());
        existingLine.setArticle(commandLineDetails.getArticle());

        return commandLineRepository.save(existingLine);
    }

    @Override
    @Transactional
    public void deleteCommandLine(Integer id) {
        if (!commandLineRepository.existsById(id)) {
            throw new RuntimeException("CommandLine not found for ID: " + id);
        }
        commandLineRepository.deleteById(id);
    }
}