package com.example.wit.entities.career.application;

import com.example.wit.entities.career.domain.CareerService;
import com.example.wit.entities.career.dto.CareerRequest;
import com.example.wit.entities.career.dto.CareerResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/career")
public class CareerController {
    @Autowired
    private CareerService service;

    @GetMapping
    public ResponseEntity<List<CareerResponse>> read () {
        return service.read();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CareerResponse> read (Short id) {
        return service.read(id);
    }

    @PostMapping
    public ResponseEntity<String> create (@Valid @RequestBody CareerRequest career) {
        return service.create(career);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update (@PathVariable Short id, @RequestBody CareerRequest career) {
        return service.update(id, career);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable Short id) {
        return service.delete(id);
    }
}
