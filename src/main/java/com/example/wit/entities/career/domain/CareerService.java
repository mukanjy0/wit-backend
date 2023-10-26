package com.example.wit.entities.career.domain;

import com.example.wit.entities.career.dto.CareerRequest;
import com.example.wit.entities.career.dto.CareerResponse;
import com.example.wit.entities.career.utils.CareerUtils;
import com.example.wit.exceptions.ElementAlreadyExistsException;
import com.example.wit.exceptions.ElementNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CareerService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CareerRepository repository;

    public ResponseEntity<List<CareerResponse>> read () {
        List<CareerResponse> careers = repository
                .findAll()
                .stream()
                .map(career -> mapper.map(career, CareerResponse.class))
                .toList();
        return new ResponseEntity<>(careers, HttpStatus.OK);
    }

    public ResponseEntity<CareerResponse> read (Short id) {
        Optional<Career> career = repository.findById(id);
        if (career.isEmpty()) {
            throw ElementNotFoundException.createWith(id.toString());
        }
        return new ResponseEntity<>(mapper.map(career.get(), CareerResponse.class), HttpStatus.OK);
    }

    public ResponseEntity<String> create (CareerRequest career) {
        String name = career.getName();
        String idName = "name";

        Optional<Career> original = repository.findCareerByName(name);
        if (original.isPresent()) {
            throw ElementAlreadyExistsException.createWith(name, idName);
        }
        repository.save(mapper.map(career, Career.class));
        return ResponseEntity.status(201).body("Career created.");
    }

    public ResponseEntity<String> update (Short id, CareerRequest career) {
        Optional<Career> original = repository.findById(id);
        if (original.isEmpty()) {
            throw ElementNotFoundException.createWith(id.toString());
        }
        Career previous = original.get();
        String name = previous.getName();

        Career updated = CareerUtils.updateCareer(original.get(), career);
        String newName = updated.getName();

        if (!name.equals(newName) && repository.existsCareerByName(newName)) {
            String idName = "name" ;
            throw ElementAlreadyExistsException.createWith(newName, idName);
        }
        repository.save(updated);
        return ResponseEntity.status(200).body("Career updated.");
    }

    public ResponseEntity<String> delete(Short id) {
        Optional<Career> career = repository.findById(id);
        if (career.isEmpty()) {
            throw ElementNotFoundException.createWith(id.toString());
        }
        repository.deleteById(id);
        return ResponseEntity.status(200).body("Career deleted.");
    }
}
