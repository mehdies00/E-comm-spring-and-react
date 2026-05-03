package com.example.demo.controller;

import com.example.demo.model.CommandLine;
import com.example.demo.service.CommandLineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandlines")
public class CommandLineController {

    private final CommandLineService commandLineService;

    public CommandLineController(CommandLineService commandLineService) {
        this.commandLineService = commandLineService;
    }

    @GetMapping
    public ResponseEntity<List<CommandLine>> getAllCommandLines() {
        List<CommandLine> commandLines = commandLineService.getAllCommandLines();
        if (commandLines.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(commandLines);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandLine> getCommandLineById(@PathVariable Integer id) {
        return commandLineService.getCommandLineById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> saveCommandLine(@RequestBody CommandLine commandLine) {
        try {
            CommandLine savedCommandLine = commandLineService.saveCommandLine(commandLine);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCommandLine);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCommandLine(@PathVariable Integer id, @RequestBody CommandLine commandLineDetails) {
        try {
            CommandLine updatedCommandLine = commandLineService.updateCommandLine(id, commandLineDetails);
            return ResponseEntity.ok(updatedCommandLine);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCommandLine(@PathVariable Integer id) {
        try {
            commandLineService.deleteCommandLine(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}