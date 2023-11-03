package com.example.wit.entities.university.domain;

import com.example.wit.exceptions.ElementAlreadyExistsException;
import com.example.wit.exceptions.ElementNotFoundException;
import com.example.wit.entities.university.dto.UniversityRequest;
import com.example.wit.entities.university.dto.UniversityResponse;
import com.example.wit.entities.university.utils.UniversityUtils;
import com.example.wit.templates.CrudService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UniversityService implements CrudService<UniversityRequest, UniversityResponse, Short> {
    @Autowired
    private UniversityRepository repository;
    @Autowired
    private ModelMapper mapper;

    public List<UniversityResponse> read() {
        return repository
                .findAll()
                .stream().map(university -> mapper.map(university, UniversityResponse.class))
                .collect(Collectors.toList());

    }

    public UniversityResponse read(Short id) {
        Optional<University> university  = repository.findById(id);
        if (university.isEmpty()) {
            throw ElementNotFoundException.createWith("University", id.toString());
        }

        return mapper.map(university.get(), UniversityResponse.class);
    }

    public void create(UniversityRequest university) {
        Optional<University> original = repository.findUniversityByFullName(university.getFullName());
        if (original.isPresent()) {
            throw ElementAlreadyExistsException.createWith(university.getFullName(), "full name");
        }

        repository.save(mapper.map(university, University.class));
    }

    public void update(Short id, UniversityRequest university) {
        Optional<University> original = repository.findById(id);
        if (original.isEmpty()) {
            throw ElementNotFoundException.createWith("University", id.toString());
        }

        University previous = original.get();
        String acronym = previous.getAcronym();
        String fullName = previous.getFullName();

        University updated = UniversityUtils.updateUniversity(previous, university);
        String newAcronym = updated.getAcronym();
        String newFullName = updated.getFullName();

        if (!acronym.equals(newAcronym)
                && repository.existsUniversityByAcronym(newAcronym)) {
            throw ElementAlreadyExistsException.createWith(newAcronym, "acronym");
        }
        if (!fullName.equals(newFullName)
                && repository.existsUniversityByFullName(newFullName)) {
            throw ElementAlreadyExistsException.createWith(newFullName, "full name");
        }

        repository.save(updated);
    }

    public void delete(Short id) {
        Optional<University> university = repository.findById(id);
        if (university.isEmpty()) {
            throw ElementNotFoundException.createWith("University", id.toString());
        }

        repository.deleteById(id);
    }
}
