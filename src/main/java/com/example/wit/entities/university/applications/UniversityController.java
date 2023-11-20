package com.example.wit.entities.university.applications;

import com.example.wit.entities.university.domain.UniversityService;
import com.example.wit.entities.university.dto.UniversityRequest;
import com.example.wit.entities.university.dto.UniversityResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/university")
public class UniversityController {
    @Autowired
    private UniversityService service;

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<List<UniversityResponse>> read (
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        return new ResponseEntity<>(service.read(page, size), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<UniversityResponse>> readAll () {
        return new ResponseEntity<>(service.readAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UniversityResponse> read (@PathVariable Short id) {
        return new ResponseEntity<>(service.read(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> create (@Valid @RequestBody UniversityRequest university) {
        service.create(university);
        return ResponseEntity.status(201).body("University created.");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> update (@PathVariable Short id, @RequestBody UniversityRequest university) {
        service.update(id, university);
        return ResponseEntity.status(200).body("University updated.");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable Short id) {
        service.delete(id);
        return ResponseEntity.status(200).body("University with id " + id.toString() + " deleted.");
    }
}
