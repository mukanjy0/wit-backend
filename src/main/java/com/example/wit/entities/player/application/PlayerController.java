package com.example.wit.entities.player.application;

import com.example.wit.entities.player.domain.UserPlayerService;
import com.example.wit.entities.player.dto.PlayerCardResponse;
import com.example.wit.entities.player.dto.PlayerResponse;
import com.example.wit.entities.player.dto.PlayerRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private UserPlayerService service;

    @GetMapping
    public ResponseEntity<List<PlayerResponse>> read (@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "5") Integer size) {
        return new ResponseEntity<>(service.read(page, size), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponse> read (@PathVariable Long id) {
        return new ResponseEntity<>(service.read(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<PlayerCardResponse>> readCards (@PathVariable Long id) {
        return new ResponseEntity<>(service.readCards(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> create (@Valid @RequestBody PlayerRequest player) {
        service.create(player);
        return ResponseEntity.status(201).body("Player created.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update (@PathVariable Long id, @RequestBody PlayerRequest player) {
        service.update(id, player);
        return ResponseEntity.status(200).body("Player updated.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(200).body("Player deleted.");
    }
}
