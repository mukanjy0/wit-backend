package com.example.wit.entities.university.domain;

import com.example.wit.exceptions.ElementAlreadyExistsException;
import com.example.wit.exceptions.ElementNotFoundException;
import com.example.wit.entities.university.dto.UniversityRequest;
import com.example.wit.entities.university.dto.UniversityResponse;
import com.example.wit.entities.university.utils.UniversityUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UniversityService {
    @Autowired
    private UniversityRepository repository;
    @Autowired
    private ModelMapper mapper;

    public ResponseEntity<List<UniversityResponse>> read() {
        List<UniversityResponse> universities = repository
                .findAll()
                .stream().map(university -> mapper.map(university, UniversityResponse.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(universities, HttpStatus.OK);
    }

    public ResponseEntity<UniversityResponse> read(Short id) {
        Optional<University> university  = repository.findById(id);
        if (university.isEmpty()) {
            throw ElementNotFoundException.createWith(id.toString());
        }

        return new ResponseEntity<>(mapper.map(university.get(), UniversityResponse.class), HttpStatus.OK);
    }

    public ResponseEntity<String> create(UniversityRequest university) {
        Optional<University> original = repository.findUniversityByFullName(university.getFullName());
        if (original.isPresent()) {
            throw ElementAlreadyExistsException.createWith(university.getFullName(), "full name");
        }

        repository.save(mapper.map(university, University.class));
        return ResponseEntity.status(201).body("University created.");
    }

    public ResponseEntity<String> update(Short id, UniversityRequest university) {
        Optional<University> original = repository.findById(id);
        if (original.isEmpty()) {
            throw ElementNotFoundException.createWith(id.toString());
        }

        repository.save(UniversityUtils.updateUniversity(original.get(), university));
        return ResponseEntity.status(200).body("University updated.");
    }

    public ResponseEntity<String> delete(Short id) {
        Optional<University> university = repository.findById(id);
        if (university.isEmpty()) {
            throw ElementNotFoundException.createWith(id.toString());
        }

        repository.deleteById(id);
        return ResponseEntity.status(200).body("University with id " + id.toString() + " deleted.");
    }
}
