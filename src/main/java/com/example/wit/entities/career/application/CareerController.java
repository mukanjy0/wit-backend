package com.example.wit.entities.career.application;

import com.example.wit.entities.career.domain.CareerService;
import com.example.wit.entities.career.dto.CareerRequest;
import com.example.wit.entities.career.dto.CareerResponse;
import com.example.wit.templates.CrudController;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/career")
public class CareerController implements CrudController<CareerRequest, CareerResponse, Short> {
    @Autowired
    private CareerService service;

    @GetMapping
    public ResponseEntity<List<CareerResponse>> read () {
        return new ResponseEntity<>(service.read(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CareerResponse> read (@PathVariable Short id) {
        return new ResponseEntity<>(service.read(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> create (@Valid @RequestBody CareerRequest career) {
        service.create(career);
        return ResponseEntity.status(201).body("Career created.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update (@PathVariable Short id, @RequestBody CareerRequest career) {
        service.update(id, career);
        return ResponseEntity.status(200).body("Career updated.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable Short id) {
        service.delete(id);
        return ResponseEntity.status(200).body("Career deleted.");
    }
}
