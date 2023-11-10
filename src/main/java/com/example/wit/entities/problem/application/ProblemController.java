package com.example.wit.entities.problem.application;

import com.example.wit.entities.problem.domain.ProblemService;
import com.example.wit.entities.problem.dto.ProblemRequest;
import com.example.wit.entities.problem.dto.ProblemResponse;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/problem")
public class ProblemController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ProblemService service;

    @GetMapping
    public ResponseEntity<List<ProblemResponse>> read () {
        return new ResponseEntity<>(service.read(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProblemResponse> read (@PathVariable Long id) {
        return new ResponseEntity<>(service.read(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> create (@Valid @RequestBody ProblemRequest problem) {
        service.create(problem);
        return ResponseEntity.status(201).body("Problem created.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update (@PathVariable Long id, @RequestBody ProblemRequest problem) {
        service.update(id, problem);
        return ResponseEntity.status(200).body("Problem updated.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(200).body("Problem deleted.");
    }
}
