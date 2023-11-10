package com.example.wit.entities.contest.domain;

import com.example.wit.entities.contest.domain.division.Division;
import com.example.wit.entities.contest.dto.ContestRequest;
import com.example.wit.entities.contest.dto.ContestResponse;
import com.example.wit.entities.contest.utils.ContestUtils;
import com.example.wit.entities.problem.domain.Problem;
import com.example.wit.entities.problem.domain.ProblemRepository;
import com.example.wit.exceptions.ElementNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@Service
public class ContestService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ContestRepository repository;
    @Autowired
    private ProblemRepository problemRepository;

    public List<ContestResponse> read () {
        return  repository
                .findAll()
                .stream()
                .map(contest -> mapper.map(contest, ContestResponse.class))
                .toList();
    }

    public ContestResponse read (Long id) {
        Optional<Contest> contest = repository.findById(id);
        if (contest.isEmpty()) {
            throw ElementNotFoundException.createWith("Contest", id.toString());
        }

        return mapper.map(contest, ContestResponse.class);
    }

    public void create (ContestRequest contest) {
        var division = contest.getDivision();
        if (division == null) {
            throw ElementNotFoundException.createWith("Division", "null");
        }
        if (Stream.of(Division.values())
                .filter(div-> div.id().equals(division))
                .toList()
                .isEmpty()) {
            throw ElementNotFoundException.createWith("Division", String.valueOf(division));
        }
        Set<Long> problemsIds = contest.getProblemIds();
        if (problemsIds != null) {
            for (Long problemId : contest.getProblemIds()) {
                if (!problemRepository.existsById(problemId)){
                    throw ElementNotFoundException.createWith("Problem", problemId.toString());
                }
            };
        }

        repository.save(mapper.map(contest, Contest.class));
    }

    public void update (Long id, ContestRequest contest) {
        Optional<Contest> original = repository.findById(id);
        if (original.isEmpty()) {
            throw ElementNotFoundException.createWith("Contest", id.toString());
        }

        Contest updated = ContestUtils.updateContest(original.get(), contest);

        Set<Problem> problemSet = updated.getProblems();
        for (Long problemId : contest.getProblemIds()) {
            Optional<Problem> problem = problemRepository.findById(problemId);
            if (problem.isEmpty()) {
                throw ElementNotFoundException.createWith("Problem", problemId.toString());
            }
            problemSet.add(problem.get());
        }
        updated.setProblems(problemSet);

        repository.save(updated);
    }

    public void delete (Long id) {
        if (repository.findById(id).isEmpty()) {
            throw ElementNotFoundException.createWith("Contest", id.toString());
        }
        repository.deleteById(id);
    }
}
