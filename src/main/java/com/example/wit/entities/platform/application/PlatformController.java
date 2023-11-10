package com.example.wit.entities.platform.application;

import com.example.wit.entities.platform.domain.PlatformService;
import com.example.wit.entities.platform.dto.PlatformRequest;
import com.example.wit.entities.platform.dto.PlatformResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/platform")
public class PlatformController {
    @Autowired
    private PlatformService service;

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<List<PlatformResponse>> read (
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        return new ResponseEntity<>(service.read(page, size), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<PlatformResponse>> readAll () {
        return new ResponseEntity<>(service.readAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PlatformResponse> read (@PathVariable Short id) {
        return new ResponseEntity<>(service.read(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> create (@Valid @RequestBody PlatformRequest platform) {
        service.create(platform);
        return ResponseEntity.status(201).body("Platform created.");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> update (@PathVariable Short id, @RequestBody PlatformRequest platform) {
        service.update(id, platform);
        return ResponseEntity.status(200).body("Platform updated.");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable Short id) {
        service.delete(id);
        return ResponseEntity.status(200).body("Platform deleted.");
    }
}
