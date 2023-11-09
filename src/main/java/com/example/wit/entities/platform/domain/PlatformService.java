package com.example.wit.entities.platform.domain;

import com.example.wit.entities.platform.dto.PlatformRequest;
import com.example.wit.entities.platform.dto.PlatformResponse;
import com.example.wit.entities.platform.utils.PlatformUtils;
import com.example.wit.exceptions.ElementAlreadyExistsException;
import com.example.wit.exceptions.ElementNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlatformService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PlatformRepository repository;

    public List<PlatformResponse> read () {
        return repository.findAll().stream().map(platform -> mapper.map(platform, PlatformResponse.class)).toList();
    }

    public PlatformResponse read (Short id) {
        Optional<Platform> platform = repository.findById(id);

        if (platform.isEmpty()) {
            throw ElementNotFoundException.createWith("Platform", id.toString());
        }

        return mapper.map(platform.get(), PlatformResponse.class);
    }

    public void create (PlatformRequest platform) {
        String name = platform.getName();
        Optional<Platform> original = repository.findPlatformByName(name);

        if (original.isPresent()) {
            throw ElementAlreadyExistsException.createWith(name, "name");
        }
        repository.save(mapper.map(platform, Platform.class));
    }

    public void update (Short id, PlatformRequest platform) {
        Optional<Platform> original = repository.findById(id);
        if (original.isEmpty()) {
            throw ElementNotFoundException.createWith("Platform", id.toString());
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
    }

    public void delete (Short id) {
        Optional<Platform> platform = repository.findById(id);
        if (platform.isEmpty()) {
            throw ElementNotFoundException.createWith("Platform", id.toString());
        }

        repository.deleteById(id);
    }
}
