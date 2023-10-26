package com.example.wit.entities.platform.application;

import com.example.wit.entities.platform.domain.PlatformService;
import com.example.wit.entities.platform.dto.PlatformRequest;
import com.example.wit.entities.platform.dto.PlatformResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/platform")
public class PlatformController {
    @Autowired
    private PlatformService service;

    @GetMapping
    public ResponseEntity<List<PlatformResponse>> read () {
        return service.read();
    }
    @GetMapping("/{id}")
    public ResponseEntity<PlatformResponse> read (@PathVariable Short id) {
        return service.read(id);
    }
    @PostMapping
    public ResponseEntity<String> create (@Valid @RequestBody PlatformRequest platform) {
        return service.create(platform);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> update (@PathVariable Short id, @RequestBody PlatformRequest platform) {
        return service.update(id, platform);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable Short id) {
        return service.delete(id);
    }
}
