package com.example.wit.entities.problem.domain;

import com.example.wit.entities.problem.dto.ProblemRequest;
import com.example.wit.entities.problem.dto.ProblemResponse;
import com.example.wit.entities.problem.utils.ProblemUtils;
import com.example.wit.entities.tag.domain.Tag;
import com.example.wit.entities.tag.domain.TagRepository;
import com.example.wit.exceptions.ElementAlreadyExistsException;
import com.example.wit.exceptions.ElementNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProblemService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ProblemRepository repository;
    @Autowired
    private TagRepository tagRepository;

    public List<ProblemResponse> read () {
        return repository.findAll()
                .stream()
                .map(problem -> mapper.map(problem, ProblemResponse.class))
                .toList();
    }

    public ProblemResponse read (Long id) {
        Optional<Problem> problem = repository.findById(id);
        if (problem.isEmpty()) {
            throw ElementNotFoundException.createWith("Problem", id.toString());
        }
        return mapper.map(problem.get(), ProblemResponse.class);
    }

    public void create (ProblemRequest problem) {
        String title = problem.getTitle();
        if (repository.existsProblemByTitle(title)) {
            throw ElementAlreadyExistsException.createWith(title, "title");
        }

        repository.save(mapper.map(problem, Problem.class));
    }

    public void update (Long id, ProblemRequest problem) {
        Optional<Problem> original = repository.findById(id);
        if (original.isEmpty()) {
            throw ElementNotFoundException.createWith("Problem", id.toString());
        }

        Problem previous = original.get();
        String title = previous.getTitle();

        Problem updated = ProblemUtils.updateProblem(original.get(), problem);
        String newTitle = updated.getTitle();

        if (!title.equals(newTitle) && repository.existsProblemByTitle(title)) {
            throw ElementAlreadyExistsException.createWith(title, "title");
        }

        Set<Tag> tags = new HashSet<>();
        for (Short tagId : problem.getTagIds()) {
            Optional<Tag> tag = tagRepository.findById(tagId);
            if (tag.isEmpty()) {
                throw ElementNotFoundException.createWith("Tag", tagId.toString());
            }
            tags.add(tag.get());
        }
        updated.setTags(tags);

        repository.save(updated);
    }

    public void delete (Long id) {
        if (repository.findById(id).isEmpty()) {
            throw ElementNotFoundException.createWith("Problem", id.toString());
        }
        repository.deleteById(id);
    }
}
