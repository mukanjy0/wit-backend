package com.example.wit.entities.player.application;

import com.example.wit.entities.player.domain.Player;
import com.example.wit.entities.player.domain.PlayerService;
import com.example.wit.entities.player.dto.PlayerResponse;
import com.example.wit.entities.player.dto.PlayerSignUp;
import com.example.wit.entities.player.dto.PlayerUpdate;
import jakarta.validation.Valid;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private PlayerService service;

    @GetMapping
    public ResponseEntity<List<PlayerResponse>> read() {
        return new ResponseEntity<>(service.read(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponse> read(@PathVariable Long id) {
        return new ResponseEntity<>(service.read(id), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody PlayerUpdate player) {
        service.update(id, player);

        return ResponseEntity.status(200).body("Player with id " + id.toString() + " updated.");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.status(200).body("Player with id " + id.toString() + " deleted.");
    }
}
