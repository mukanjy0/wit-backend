package com.example.wit.entities.team.application;

import com.example.wit.entities.team.domain.TeamService;
import com.example.wit.entities.team.dto.TeamRequest;
import com.example.wit.entities.team.dto.TeamResponse;
import com.example.wit.templates.CrudController;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController implements CrudController<TeamRequest, TeamResponse, Long> {
    @Autowired
    private TeamService service;

    @GetMapping
    public ResponseEntity<List<TeamResponse>> read () {
        return new ResponseEntity<>(service.read(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TeamResponse> read (@PathVariable Long id) {
        return new ResponseEntity<>(service.read(id), HttpStatus.OK);
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
