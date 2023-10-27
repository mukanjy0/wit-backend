package com.example.wit.entities.player.domain;

import com.example.wit.entities.career.domain.Career;
import com.example.wit.entities.career.domain.CareerRepository;
import com.example.wit.entities.player.domain.category.Category;
import com.example.wit.entities.player.domain.role.Role;
import com.example.wit.entities.player.dto.PlayerResponse;
import com.example.wit.entities.player.dto.PlayerSignUp;
import com.example.wit.entities.player.dto.PlayerUpdate;
import com.example.wit.entities.team.domain.Team;
import com.example.wit.entities.team.domain.TeamRepository;
import com.example.wit.entities.university.domain.University;
import com.example.wit.entities.university.domain.UniversityRepository;
import com.example.wit.exceptions.ElementAlreadyExistsException;
import com.example.wit.exceptions.ElementNotFoundException;
import com.example.wit.entities.player.utils.PlayerUtils;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class PlayerService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PlayerRepository repository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private CareerRepository careerRepository;
    @Autowired
    private UniversityRepository universityRepository;

//    @PostConstruct
//    private void configureModelMapper() {
//    }

    public ResponseEntity<List<PlayerResponse>> read () {
        List<PlayerResponse> players = repository.findAll().stream().map(player -> mapper.map(player, PlayerResponse.class)).toList();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    public ResponseEntity<PlayerResponse> read (Long id) throws ElementNotFoundException {
        Optional<Player>  player = repository.findById(id);
        if (player.isEmpty()) {
            throw ElementNotFoundException.createWith("Player", id.toString());
        }

        return new ResponseEntity<>(mapper.map(player.get(), PlayerResponse.class), HttpStatus.OK);
    }
    public ResponseEntity<String> create (PlayerSignUp player) {
        Player newPlayer = mapper.map(player, Player.class);
        newPlayer.setPassword(player.getPassword());
        newPlayer.setBestCategory(newPlayer.getCurrentCategory());
        newPlayer.setRegistrationDate(LocalDate.now());

        repository.save(newPlayer);

        return ResponseEntity.status(201).body("Player created.");
    }

    public ResponseEntity<String> update (Long id, PlayerUpdate player) throws ElementNotFoundException, IllegalArgumentException {
        Optional<Player> original = repository.findById(id);
        if (original.isEmpty()) {
            throw ElementNotFoundException.createWith("Player", id.toString());
        }

        Optional<Team> team = teamRepository.findById(player.getTeamId());
        if (team.isEmpty()) {
            throw ElementNotFoundException.createWith("Team", player.getTeamId().toString());
        }

        Optional<Career> career = careerRepository.findById(player.getCareerId());
        if (career.isEmpty()) {
            throw ElementNotFoundException.createWith("Career", player.getCareerId().toString());
        }

        Optional<University> university = universityRepository.findById(player.getUniversityId());
        if (university.isEmpty()) {
            throw ElementNotFoundException.createWith("University", player.getUniversityId().toString());
        }

        Player previous = original.get();
        String username = previous.getUsername();

        Player updated = PlayerUtils.updatePlayer(previous, player);
        String newUsername = updated.getUsername();

        if (!username.equals(newUsername) && repository.existsPlayerByUsername(newUsername)) {
            throw ElementAlreadyExistsException.createWith(newUsername, "username");
        }

        updated.setTeam(team.get());
        updated.setCareer(career.get());
        updated.setUniversity(university.get());

        repository.save(updated);

        return ResponseEntity.status(200).body("Player with id " + id.toString() + " updated.");
    }

    public ResponseEntity<String> delete (Long id) throws ElementNotFoundException {
        Optional<Player> player = repository.findById(id);
        if (player.isEmpty()) {
            throw ElementNotFoundException.createWith("Player", id.toString());
        }
        repository.deleteById(id);
        return ResponseEntity.status(200).body("Player with id " + id.toString() + " deleted.");
    }
}
