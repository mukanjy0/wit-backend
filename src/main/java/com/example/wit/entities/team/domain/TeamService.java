package com.example.wit.entities.team.domain;

import com.example.wit.entities.team.dto.TeamRequest;
import com.example.wit.entities.team.dto.TeamResponse;
import com.example.wit.entities.team.utils.TeamUtils;
import com.example.wit.exceptions.ElementAlreadyExistsException;
import com.example.wit.exceptions.ElementNotFoundException;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private TeamRepository repository;

    public ResponseEntity<List<TeamResponse>> read () {
       List<TeamResponse> teams = repository.findAll().stream().map(team -> mapper.map(team, TeamResponse.class)).toList();
       return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    public ResponseEntity<TeamResponse> read (Long id) {
        Optional<Team> team = repository.findById(id);
        if (team.isEmpty()) {
            throw ElementNotFoundException.createWith(id.toString());
        }

        return new ResponseEntity<>(mapper.map(team.get(), TeamResponse.class), HttpStatus.OK);
    }

    public ResponseEntity<String> create (TeamRequest team) {
        String name = team.getName();
        Optional<Team> original = repository.findTeamByName(name);

        if (original.isPresent()) {
            throw ElementAlreadyExistsException.createWith(name, "name");
        }

        repository.save(mapper.map(team, Team.class));
        return ResponseEntity.status(201).body("Team created.");
    }

    public ResponseEntity<String> update (Long id, TeamRequest team) {
        Optional<Team> original = repository.findById(id);
        if (original.isEmpty()) {
            throw ElementNotFoundException.createWith(id.toString());
        }

        Team previous = original.get();
        String previousName = previous.getName();

        Team updated = TeamUtils.updateTeam(previous, team);
        String newName = updated.getName();

        if (!previousName.equals(newName) && repository.existsTeamByName(newName)) {
            throw ElementAlreadyExistsException.createWith(newName, "name");
        }

        repository.save(updated);
        return ResponseEntity.status(200).body("Team updated.");
    }

    public ResponseEntity<String> delete (Long id) {
        Optional<Team> team = repository.findById(id);
        if (team.isEmpty()) {
            throw ElementNotFoundException.createWith(id.toString());
        }

        repository.deleteById(id);
        return ResponseEntity.status(200).body("Team deleted.");
    }
}
