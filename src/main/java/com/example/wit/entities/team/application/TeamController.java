package com.example.wit.entities.team.application;

import com.example.wit.entities.team.domain.TeamService;
import com.example.wit.entities.team.dto.TeamRequest;
import com.example.wit.entities.team.dto.TeamResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService service;

    @GetMapping
    public ResponseEntity<List<TeamResponse>> read () {
        return service.read();
    }
    @GetMapping("/{id}")
    public ResponseEntity<TeamResponse> read (@PathVariable Long id) {
        return service.read(id);
    }
    @PostMapping
    public ResponseEntity<String> create (@Valid @RequestBody TeamRequest team) {
        return service.create(team);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> update (@PathVariable Long id, @RequestBody TeamRequest team) {
        return service.update(id, team);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable Long id) {
        return service.delete(id);
    }
}
