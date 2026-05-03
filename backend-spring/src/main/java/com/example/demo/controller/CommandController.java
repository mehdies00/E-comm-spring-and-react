package com.example.demo.controller;

import com.example.demo.model.Command;
import com.example.demo.service.CommandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commands")
public class CommandController {

    private final CommandService commandService;

    public CommandController(CommandService commandService) {
        this.commandService = commandService;
    }

    @GetMapping
    public ResponseEntity<List<Command>> getAllCommands() {
        List<Command> commands = commandService.getAllCommands();
        if (commands.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(commands);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Command> getCommandById(@PathVariable Integer id) {
        return commandService.getCommandById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> saveCommand(@RequestBody Command command) {
        try {
            Command savedCommand = commandService.saveCommand(command);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCommand);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCommand(@PathVariable Integer id, @RequestBody Command commandDetails) {
        try {
            Command updatedCommand = commandService.updateCommand(id, commandDetails);
            return ResponseEntity.ok(updatedCommand);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCommand(@PathVariable Integer id) {
        try {
            commandService.deleteCommand(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}