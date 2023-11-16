package com.example.wit.entities.team.application;

import com.example.wit.entities.player.dto.PlayerResponse;
import com.example.wit.entities.team.domain.TeamService;
import com.example.wit.entities.team.dto.TeamRequest;
import com.example.wit.entities.team.dto.TeamResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService service;

    @GetMapping
    public ResponseEntity<List<TeamResponse>> read (
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return new ResponseEntity<>(service.read(page, size), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TeamResponse> read (@PathVariable Long id) {
        return new ResponseEntity<>(service.read(id), HttpStatus.OK);
    }
    @GetMapping(params = {"name"})
    public ResponseEntity<TeamResponse> read (@RequestParam String name) {
        return new ResponseEntity<>(service.read(name), HttpStatus.OK);
    }
    @GetMapping("/{id}/members")
    public ResponseEntity<List<PlayerResponse>> readMembers (@PathVariable Long id) {
        return new ResponseEntity<>(service.readMembers(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> create (@Valid @RequestBody TeamRequest team) {
        service.create(team);
        return ResponseEntity.status(201).body("Team created.");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> update (@PathVariable Long id, @RequestBody TeamRequest team) {
        service.update(id, team);
        return ResponseEntity.status(200).body("Team updated.");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(200).body("Team deleted.");
    }
}
