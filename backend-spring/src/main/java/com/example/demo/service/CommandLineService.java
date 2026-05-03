package com.example.demo.service;

import com.example.demo.model.CommandLine;
import java.util.List;
import java.util.Optional;

public interface CommandLineService {
    List<CommandLine> getAllCommandLines();

    Optional<CommandLine> getCommandLineById(Integer id);

    CommandLine saveCommandLine(CommandLine commandLine);

    CommandLine updateCommandLine(Integer id, CommandLine commandLineDetails);

    void deleteCommandLine(Integer id);
}