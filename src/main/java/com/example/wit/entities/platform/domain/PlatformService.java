package com.example.wit.entities.platform.domain;

import com.example.wit.entities.platform.dto.PlatformRequest;
import com.example.wit.entities.platform.dto.PlatformResponse;
import com.example.wit.entities.platform.utils.PlatformUtils;
import com.example.wit.exceptions.ElementAlreadyExistsException;
import com.example.wit.exceptions.ElementNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlatformService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PlatformRepository repository;

    public ResponseEntity<List<PlatformResponse>> read () {
        List<PlatformResponse> platforms = repository.findAll().stream().map(platform -> mapper.map(platform, PlatformResponse.class)).toList();
        return new ResponseEntity<>(platforms, HttpStatus.OK);
    }

    public ResponseEntity<PlatformResponse> read (Short id) {
        Optional<Platform> platform = repository.findById(id);

        if (platform.isEmpty()) {
            throw ElementNotFoundException.createWith(id.toString());
        }

        return new ResponseEntity<>(mapper.map(platform.get(), PlatformResponse.class), HttpStatus.OK);
    }

    public ResponseEntity<String> create (PlatformRequest platform) {
        String name = platform.getName();
        Optional<Platform> original = repository.findPlatformByName(name);
        if (original.isPresent()) {
            throw ElementAlreadyExistsException.createWith(name, "name");
        }

        repository.save(mapper.map(platform, Platform.class));
        return ResponseEntity.status(201).body("Platform created.");
    }

    public ResponseEntity<String> update (Short id, PlatformRequest platform) {
        Optional<Platform> original = repository.findById(id);
        if (original.isEmpty()) {
            throw ElementNotFoundException.createWith(id.toString());
        }

        Platform previous = original.get();
        String name = previous.getName();
        String url = previous.getUrl();

        Platform updated = PlatformUtils.updatePlatform(previous, platform);
        String newName = updated.getName();
        String newUrl = updated.getUrl();

        if (!name.equals(newName) && repository.existsPlatformByName(newName)) {
            throw ElementAlreadyExistsException.createWith(newName, "name");
        }

        if (!url.equals(newUrl) && repository.existsPlatformByUrl(newUrl)) {
            throw ElementAlreadyExistsException.createWith(newUrl, "url");
        }

        repository.save(updated);
        return ResponseEntity.status(200).body("Platform updated.");
    }

    public ResponseEntity<String> delete (Short id) {
        Optional<Platform> platform = repository.findById(id);
        if (platform.isEmpty()) {
            throw ElementNotFoundException.createWith(id.toString());
        }

        repository.deleteById(id);
        return ResponseEntity.status(200).body("Platform deleted.");
    }
}
