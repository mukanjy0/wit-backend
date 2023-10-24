package com.example.wit.entities.university.applications;

import com.example.wit.entities.university.domain.UniversityRepository;
import com.example.wit.entities.university.domain.UniversityService;
import com.example.wit.entities.university.dto.UniversityRequest;
import com.example.wit.entities.university.dto.UniversityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/university")
public class UniversityController {
    @Autowired
    private UniversityService service;

    @GetMapping
    public ResponseEntity<List<UniversityResponse>> read () {
        return service.read();
    }
    @GetMapping("/{id}")
    public ResponseEntity<UniversityResponse> read (@PathVariable Short id) {
        return service.read(id);
    }
    @PostMapping
    public ResponseEntity<String> create (@RequestBody UniversityRequest university) {
        return service.create(university);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> create (@PathVariable Short id, @RequestBody UniversityRequest university) {
        return service.update(id, university);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> create (@PathVariable Short id) {
        return service.delete(id);
    }
}
