package com.example.wit.entities.player.application;

import com.example.wit.entities.player.domain.Player;
import com.example.wit.entities.player.domain.PlayerService;
import com.example.wit.entities.player.dto.PlayerSignUp;
import jakarta.validation.Valid;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private PlayerService service;
    @GetMapping
    public ResponseEntity<List<Player>> read() {
        return service.read();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Player> read(@PathVariable Long id) {
        return service.read(id);
    }
    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody PlayerSignUp player) {
        return service.create(player);
    }
}
