package com.example.wit.entities.submission.domain;

import com.example.wit.entities.player.domain.Player;
import com.example.wit.entities.player.domain.PlayerRepository;
import com.example.wit.entities.problem.domain.Problem;
import com.example.wit.entities.problem.domain.ProblemRepository;
import com.example.wit.entities.submission.dto.SubmissionRequest;
import com.example.wit.entities.submission.dto.SubmissionResponse;
import com.example.wit.entities.submission.utils.SubmissionUtils;
import com.example.wit.exceptions.ElementAlreadyExistsException;
import com.example.wit.exceptions.ElementNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private SubmissionRepository repository;
    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private PlayerRepository playerRepository;

    public List<SubmissionResponse> read () {
        return repository.findAll()
                .stream()
                .map(problem -> mapper.map(problem, SubmissionResponse.class))
                .toList();
    }

    public SubmissionResponse read (Long id) {
        Optional<Submission> problem = repository.findById(id);
        if (problem.isEmpty()) {
            throw ElementNotFoundException.createWith("Submission", id.toString());
        }
        return mapper.map(problem.get(), SubmissionResponse.class);
    }

    public void create (SubmissionRequest submission) {
        Long playerId = submission.getPlayerId();
        Long problemId = submission.getProblemId();
        LocalDateTime dateTime = submission.getDateTime();

        if (!playerRepository.existsById(playerId)) {
            throw ElementNotFoundException.createWith("Player", playerId.toString());
        }
        if (!problemRepository.existsById(problemId)) {
            throw ElementNotFoundException.createWith("Problem", problemId.toString());
        }

        if (repository.findSubmissionByProblemIdAndPlayerIdAndDateTime(playerId, problemId, dateTime).isPresent()) {
            throw ElementAlreadyExistsException
                    .createWith(problemId.toString() + "-" + playerId.toString() + "-" + dateTime.toString(),
                            "(problem_id, player_id, date_time)");
        }

        repository.save(mapper.map(submission, Submission.class));
    }

    public void update (Long id, SubmissionRequest submission) {
        Optional<Submission> original = repository.findById(id);
        if (original.isEmpty()) {
            throw ElementNotFoundException.createWith("Submission", id.toString());
        }

        Submission previous = original.get();
        Long problemId = previous.getProblem().getId();
        Long playerId = previous.getPlayer().getId();

        Submission updated = SubmissionUtils.updateSubmission(original.get(), submission);
        Long newProblemId = updated.getProblem().getId();
        Long newPlayerId = updated.getPlayer().getId();

        if (newProblemId != null && !problemId.equals(newProblemId)) {
            Optional<Problem> problem = problemRepository.findById(newProblemId);
            if (problem.isEmpty()) {
                throw ElementNotFoundException.createWith("Problem", newProblemId.toString());
            }
            updated.setProblem(problem.get());
        }

        if (newPlayerId != null && !playerId.equals(newPlayerId)) {
            Optional<Player> player = playerRepository.findById(newPlayerId);
            if (player.isEmpty()) {
                throw ElementNotFoundException.createWith("Player", newPlayerId.toString());
            }
            updated.setPlayer(player.get());
        }

        playerId = updated.getPlayer().getId();
        problemId = updated.getProblem().getId();
        LocalDateTime dateTime = updated.getDateTime();
        if (repository.findSubmissionByProblemIdAndPlayerIdAndDateTime(playerId, problemId, dateTime).isPresent()) {
            throw ElementAlreadyExistsException
                    .createWith(problemId.toString() + "-" + playerId.toString() + "-" + dateTime.toString(),
                                "(problem_id, player_id, date_time)");
        }

        repository.save(updated);
    }

    public void delete (Long id) {
        if (repository.findById(id).isEmpty()) {
            throw ElementNotFoundException.createWith("Submission", id.toString());
        }
        repository.deleteById(id);
    }
}
