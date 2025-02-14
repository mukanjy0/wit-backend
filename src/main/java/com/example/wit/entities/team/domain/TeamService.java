package com.example.wit.entities.team.domain;

import com.example.wit.entities.player.dto.PlayerResponse;
import com.example.wit.entities.team.dto.TeamRequest;
import com.example.wit.entities.team.dto.TeamResponse;
import com.example.wit.entities.team.utils.TeamUtils;
import com.example.wit.exceptions.ElementAlreadyExistsException;
import com.example.wit.exceptions.ElementNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private TeamRepository repository;

    public List<TeamResponse> read (Integer page, Integer size) {
        List<TeamResponse> teams = repository.findAll(PageRequest.of(page, size))
                .stream()
                .map(team -> mapper.map(team, TeamResponse.class))
                .toList();
        teams.forEach(team -> team.setRank(repository.getTeamRank(team.getId())));
        return teams;
    }

    public TeamResponse read (Long id) {
        Optional<Team> team = repository.findById(id);
        if (team.isEmpty()) {
            throw ElementNotFoundException.createWith("Team", id.toString());
        }

        TeamResponse response = mapper.map(team.get(), TeamResponse.class);
        response.setRank(repository.getTeamRank(id));
        return response;
    }

    public TeamResponse read (String name) {
        Optional<Team> team = repository.findTeamByName(name);
        if (team.isEmpty()) {
            throw ElementNotFoundException.createWith("Team", name);
        }

        TeamResponse response = mapper.map(team.get(), TeamResponse.class);
        response.setRank(repository.getTeamRank(response.getId()));
        return response;
    }

    public List<PlayerResponse> readMembers (Long id) {
        Optional<Team> team = repository.findById(id);
        if (team.isEmpty()) {
            throw ElementNotFoundException.createWith("Team", id.toString());
        }
        return team.get().getPlayers()
                .stream()
                .map(player -> mapper.map(player, PlayerResponse.class))
                .toList();
    }

    public void create (TeamRequest team) {
        String name = team.getName();
        Optional<Team> original = repository.findTeamByName(name);

        if (original.isPresent()) {
            throw ElementAlreadyExistsException.createWith(name, "name");
        }

        repository.save(mapper.map(team, Team.class));
    }

    public void update (Long id, TeamRequest team) {
        Optional<Team> original = repository.findById(id);
        if (original.isEmpty()) {
            throw ElementNotFoundException.createWith("Team", id.toString());
        }

        Team previous = original.get();
        String previousName = previous.getName();

        Team updated = TeamUtils.updateTeam(previous, team);
        String newName = updated.getName();

        if (!previousName.equals(newName) && repository.existsTeamByName(newName)) {
            throw ElementAlreadyExistsException.createWith(newName, "name");
        }

        repository.save(updated);
    }

    public void delete (Long id) {
        Optional<Team> team = repository.findById(id);
        if (team.isEmpty()) {
            throw ElementNotFoundException.createWith("Team", id.toString());
        }

        repository.deleteById(id);
    }
}
