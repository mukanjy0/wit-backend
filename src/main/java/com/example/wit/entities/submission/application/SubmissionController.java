package com.example.wit.entities.submission.application;

import com.example.wit.entities.submission.domain.SubmissionService;
import com.example.wit.entities.submission.dto.SubmissionRequest;
import com.example.wit.entities.submission.dto.SubmissionResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submission")
public class SubmissionController {
    @Autowired
    private SubmissionService service;

    @GetMapping
    public ResponseEntity<List<SubmissionResponse>> read () {
        return new ResponseEntity<>(service.read(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubmissionResponse> read (@PathVariable Long id) {
        return new ResponseEntity<>(service.read(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> create (@Valid @RequestBody SubmissionRequest submission) {
        service.create(submission);
        return ResponseEntity.status(201).body("Submission created.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update (@PathVariable Long id, @RequestBody SubmissionRequest submission) {
        service.update(id, submission);
        return ResponseEntity.status(200).body("Submission updated.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(200).body("Submission deleted.");
    }

}
