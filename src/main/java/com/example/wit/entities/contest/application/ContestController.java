package com.example.wit.entities.problem.application;

import com.example.wit.entities.contest.domain.ContestService;
import com.example.wit.entities.contest.dto.ContestRequest;
import com.example.wit.entities.contest.dto.ContestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contest")
public class ContestController {
    @Autowired
    private ContestService service;

    @GetMapping
    public ResponseEntity<List<ContestResponse>> read () {
        return new ResponseEntity<>(service.read(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContestResponse> read (@PathVariable Long id) {
        return new ResponseEntity<>(service.read(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> create (@RequestBody ContestRequest contest) {
        service.create(contest);
        return ResponseEntity.status(201).body("Contest created.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update (@PathVariable Long id, @RequestBody ContestRequest contest) {
        service.update(id, contest);
        return ResponseEntity.status(200).body("Contest updated.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable Long id) {
        return ResponseEntity.status(200).body("Contest deleted.");
    }
}
