package com.example.wit.entities.career.domain;

import com.example.wit.entities.career.dto.CareerRequest;
import com.example.wit.entities.career.dto.CareerResponse;
import com.example.wit.entities.career.utils.CareerUtils;
import com.example.wit.exceptions.ElementAlreadyExistsException;
import com.example.wit.exceptions.ElementNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CareerService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CareerRepository repository;

    public List<CareerResponse> read (Integer page, Integer size) {
        return repository
                .findAll(PageRequest.of(page, size))
                .stream()
                .map(career -> mapper.map(career, CareerResponse.class))
                .toList();
    }

    public List<CareerResponse> readAll () {
        return repository
                .findAll()
                .stream()
                .map(career -> mapper.map(career, CareerResponse.class))
                .toList();
    }

    public CareerResponse read (Short id) {
        Optional<Career> career = repository.findById(id);
        if (career.isEmpty()) {
            throw ElementNotFoundException.createWith("Career", id.toString());
        }
        return mapper.map(career.get(), CareerResponse.class);
    }

    public void create (CareerRequest career) {
        String name = career.getName();

        Optional<Career> original = repository.findCareerByName(name);
        if (original.isPresent()) {
            throw ElementAlreadyExistsException.createWith(name, "name");
        }
        repository.save(mapper.map(career, Career.class));
    }

    public void update (Short id, CareerRequest career) {
        Optional<Career> original = repository.findById(id);
        if (original.isEmpty()) {
            throw ElementNotFoundException.createWith("Career", id.toString());
        }
        Career previous = original.get();
        String name = previous.getName();

        Career updated = CareerUtils.updateCareer(original.get(), career);
        String newName = updated.getName();

        if (!name.equals(newName) && repository.existsCareerByName(newName)) {
            throw ElementAlreadyExistsException.createWith(newName, "name");
        }
        repository.save(updated);
    }

    public void delete(Short id) {
        Optional<Career> career = repository.findById(id);
        if (career.isEmpty()) {
            throw ElementNotFoundException.createWith("Career", id.toString());
        }
        repository.deleteById(id);
    }
}
