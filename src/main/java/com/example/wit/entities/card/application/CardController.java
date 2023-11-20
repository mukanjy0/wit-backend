package com.example.wit.entities.card.application;

import com.example.wit.entities.card.domain.CardService;
import com.example.wit.entities.card.dto.CardRequest;
import com.example.wit.entities.card.dto.CardResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService service;

    @GetMapping
    public ResponseEntity<List<CardResponse>> read () {
        return new ResponseEntity<>(service.read(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CardResponse> read (@PathVariable Short id) {
        return new ResponseEntity<>(service.read(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> create (@Valid @RequestBody CardRequest card) {
        service.create(card);
        return ResponseEntity.status(201).body("Card created.");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> update (@PathVariable Short id, @RequestBody CardRequest card) {
        service.update(id, card);
        return ResponseEntity.status(200).body("Card updated.");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable Short id) {
        service.delete(id);
        return ResponseEntity.status(200).body("Card with id " + id.toString() + " deleted.");
    }
}
